package top.ctong.wisdom.common.log.print;

import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.ctong.wisdom.common.ErrorCode;
import top.ctong.wisdom.common.http.service.LogServiceClient;
import top.ctong.wisdom.common.log.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀     ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒      ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░      ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄      ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄     ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒     ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 * Copyright 2023 Clover You.
 * <p>
 * 请求日志输出
 * </p>
 *
 * @author Clover
 * @date 2023-06-20 14:34
 */
@Aspect
@Component
public class LogAspect {

    @Resource
    private LogServiceClient logServiceClient;

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(top.ctong.wisdom.common.log.Log)")
    public Object print(ProceedingJoinPoint joinPoint) throws Throwable {
        var begin = System.nanoTime();

        try {
            return joinPoint.proceed();
        } finally {
            var current_request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert current_request != null : "`@Log` 应该在 controller 中使用";

            var request = current_request.getRequest();
            var methodSignature = (MethodSignature) joinPoint.getSignature();
            var targetMethod = methodSignature.getMethod();

            /*
             * 请求方式：例如 GET、POST、DELETE 等。
             * 请求 URL：用户请求的 URL 地址。
             * 请求参数：用户传递的请求参数。
             * 请求头信息：用户传递的请求头信息，例如 User-Agent、Accept、Content-Type 等。
             * 客户端 IP：请求来源的客户端 IP 地址。
             * 请求的时间戳：记录请求发生的时间。
             * 用户 ID：如果有用户登陆，可以记录其用户 ID。
             * 操作的结果：记录请求的操作结果，例如成功或者失败。
             * 错误信息：如果请求出现了错误，需要记录错误信息，例如错误代码、错误消息等等。
             * 响应数据：如果响应数据较为小，则可以将响应数据一并记录下来。
             * 请求耗时：记录请求从发生到处理的时间，用于性能分析。
             * Session ID：每个会话都有一个唯一的ID，记录该ID可以在跟踪会话过程中很有用。
             * 请求的 Referer：记录请求来源的 URL 地址，可以帮助追踪用户从哪里访问网站。
             * 状态码：记录请求的状态码，例如 200、404、500 等等。
             * 请求体：如果请求体较小且包括重要数据，则可以记录请求体的内容。
             * 响应头信息：记录服务器响应头信息，例如Content-Length、Cache-Control、Server 等。
             * 应用程序版本信息：每次请求时记录应用程序的版本信息，方便后续排查问题。
             */
            var logAnnotation = targetMethod.getAnnotation(Log.class);

            var req_method = request.getMethod();
            var req_url = request.getRequestURL();
            var req_params = request.getParameterMap();
            var headerNames = request.getHeaderNames();
            var remoteAddr = request.getRemoteAddr();
            var sessionId = request.getRequestedSessionId();
            var referer = request.getHeader("referer");
            Object requestBody = null;
            List<Object> pathParams = null;

            var methodList = joinPoint.getArgs();
            // 获取请求体数据
            int bodyParamIndex = findRequestBodyParamIndex(methodSignature.getMethod());
            if (bodyParamIndex > -1) {
                requestBody = methodList[bodyParamIndex];
            }

            // 获取请求路径参数
            List<Integer> pathVariableParamIndex = collectPathVariableParamIndex(methodSignature.getMethod());
            pathParams = new ArrayList<>(pathVariableParamIndex.size());
            for (int index : pathVariableParamIndex) {
                pathParams.add(methodList[index]);
            }

            var className = joinPoint.getTarget().getClass().getName();
            var methodName = joinPoint.getSignature().getName();

            var end = System.nanoTime() - begin;

            log.debug("{}@{} Execution time ===> {} ", className, methodName, end);

            logServiceClient.saveLog().subscribe(result -> {
                if (result.equals(ErrorCode.SUCCESS)) {
                    log.info("日志 保存成功");
                } else {
                    log.error("日志保存失败 {}", result.message());
                }
            });
        }

    }

    /**
     * 寻找请求体对应的参数位置
     *
     * @param method 目标方法
     * @return int
     * @author Clover You
     * @date 2023/6/20 17:19
     */
    private int findRequestBodyParamIndex(Method method) {
        var collect = findTargetParamsAnnotationIndex(method, RequestBody.class);
        if (collect.isEmpty()) return -1;
        // SpringBoot 默认取请求体数据只能取一次，所以直接指定第一个就可以。因为如果出现两个 @RequestBody ，会发生管道被提前关闭异常
        return collect.get(0);
    }

    /**
     * 获取所有路径参数
     *
     * @param method 请求处理方法
     * @return int[]
     * @author Clover You
     * @date 2023/6/20 17:44
     */
    private List<Integer> collectPathVariableParamIndex(Method method) {
        return findTargetParamsAnnotationIndex(method, PathVariable.class);
    }

    /**
     * 获取指定注解所在的位置
     *
     * @param method 请求处理方法
     * @param types  注解参数
     * @return int[]
     * @author Clover You
     * @date 2023/6/20 17:45
     */
    private List<Integer> findTargetParamsAnnotationIndex(Method method, Class<? extends Annotation> types) {
        int pc = method.getParameterCount();
        if (pc == 0) return new ArrayList<>(0);

        var parameterAnnotations = method.getParameterAnnotations();
        var index = -1;
        var collect = new ArrayList<Integer>(pc);

        for (Annotation[] annotations : parameterAnnotations) {
            index++;
            for (Annotation annotation : annotations) {
                if (types.isInstance(annotation)) {
                    collect.add(index);
                }
            }
        }
        return collect;
    }

}
