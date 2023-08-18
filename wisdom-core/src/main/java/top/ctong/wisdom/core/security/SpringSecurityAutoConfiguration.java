package top.ctong.wisdom.core.security;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.ctong.wisdom.common.ErrorCode;
import top.ctong.wisdom.common.utils.R;
import top.ctong.wisdom.common.http.service.UserServiceClient;
import top.ctong.wisdom.common.model.entity.User;
import top.ctong.wisdom.common.utils.BeanUtils;
import top.ctong.wisdom.core.security.filter.DefaultJwtAuthenticationFilter;

import java.util.Collections;
import java.util.TreeSet;

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
 * spring security 自动配置类
 * </p>
 *
 * @author Clover
 * @date 2023-06-22 16:25
 */
@EnableWebSecurity
@ConditionalOnClass(SecurityAutoConfiguration.class)
@Import(DefaultJwtAuthenticationFilter.class)
@EnableConfigurationProperties(SecurityConfigProperties.class)
@Configuration
public class SpringSecurityAutoConfiguration {

    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(SpringSecurityAutoConfiguration.class);

    private CustomSecurityConfig customConfig = new CustomSecurityConfig() {};

    @Autowired(required = false)
    public void setRequestMatchers(CustomSecurityConfig config) {
        this.customConfig = config;
    }

    private String[][] getRequestMatchers() {
        var permitArr = new String[]{
            "/favicon.ico",
            "/v3/api-docs",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/index.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-initializer.js",
            "/v3/api-docs/swagger-config",
            "/swagger-ui/favicon-32x32.png",
        };

        var postPermit = new String[0];
        var getPermit = new String[0];

        var anyPaths = new TreeSet<String>();

        Collections.addAll(anyPaths, permitArr);
        Collections.addAll(anyPaths, customConfig.permitAll());

        permitArr = anyPaths.toArray(new String[0]);

        postPermit = customConfig.postPermitAll();
        getPermit = customConfig.getPermitAll();

        return new String[][]{permitArr, getPermit, postPermit};
    }

    @Bean
    public UserDetailsService userDetailsService(UserServiceClient userServiceClient) {
        // 这个 username 通常是 user id
        return username -> {
            var userId = Long.valueOf(username);
            var userInfoByUsername = userServiceClient.getUserInfoByUsername(userId);
            var result = userInfoByUsername.block();

            // 检查远程响应是否成功
            assert result != null;
            if (result.code() != ErrorCode.SUCCESS.getCode()) {
                return null;
            }

            User user = new User();
            BeanUtils.copyProperties(result.data(), user);

            return new UserDetailsProvider(user);
        };
    }

    /**
     * 默认的未登录异常处理器
     *
     * @return AuthenticationEntryPoint
     * @author Clover You
     * @date 2023/6/27 11:58
     */
    @Bean
    @ConditionalOnMissingBean(AuthenticationEntryPoint.class)
    public AuthenticationEntryPoint entryPoint() {
        return (request, response, authException) -> {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            try (var writer = response.getWriter()) {

                var result = R.fail(ErrorCode.NOT_LOGIN_ERROR);
                writer.println(result.json());
                writer.flush();
            } catch (Exception e) {
                log.error("AuthenticationEntryPoint ", e);
            }
        };
    }

    /**
     * 默认的无权限异常处理器
     *
     * @return AccessDeniedHandler
     * @author Clover You
     * @date 2023/6/27 11:57
     */
    @Bean
    @ConditionalOnMissingBean(AccessDeniedHandler.class)
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            try (var writer = response.getWriter()) {

                var result = R.fail(ErrorCode.NO_AUTH_ERROR);
                writer.println(result.json());
                writer.flush();
            } catch (Exception e) {
                log.error("AccessDeniedHandler ", e);
            }
        };
    }

    @Resource(type = SecurityConfigProperties.class)
    SecurityConfigProperties properties;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain filterChain(
        HttpSecurity http,
        AuthenticationEntryPoint entryPoint,
        AccessDeniedHandler accessDeniedHandler,
        UserDetailsService userDetailsService
//        @Resource(name = "defaultSecurityBeforeFilter") SecurityBeforeFilter beforeFilter
    ) throws Exception {

        var matchers = getRequestMatchers();

        return http
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(it -> it
                .requestMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .requestMatchers(
                    matchers[0]
                )
                .permitAll()
                .requestMatchers(HttpMethod.GET, matchers[1])
                .permitAll()
                .requestMatchers(HttpMethod.POST, matchers[2])
                .permitAll()
                .anyRequest()
                .authenticated()
            )
            // 校验异常处理器
            .exceptionHandling(it -> it
                // 未登录
                .authenticationEntryPoint(entryPoint)
                // 权限验证未通过
                .accessDeniedHandler(accessDeniedHandler)
            )
            // 登录拦截器，做一个能够对 user-server 发起远程调用
            .addFilterBefore(
                customConfig.loginFilter(properties, userDetailsService),
                UsernamePasswordAuthenticationFilter.class
            )
            .build();
    }

}
