package top.ctong.wisdom.user.controller;

import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ctong.wisdom.common.ErrorCode;
import top.ctong.wisdom.common.utils.R;
import top.ctong.wisdom.common.exception.ThrowUtils;
import top.ctong.wisdom.common.log.Log;
import top.ctong.wisdom.common.model.dto.user.auth.UserMobileRegisterRequest;
import top.ctong.wisdom.common.model.dto.user.auth.UserMobilePhoneLoginRequest;
import top.ctong.wisdom.core.security.SecurityConfigProperties;
import top.ctong.wisdom.user.service.UserService;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

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
 * 登录授权
 * </p>
 *
 * @author Clover
 * @date 2023-06-20 11:44
 */
@RequestMapping("/auth")
@RestController
@Tag(description = "登录授权", name = "auth-controller")
public class AuthController {

    @Resource
    private UserService userService;

    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Resource(type = SecurityConfigProperties.class)
    private SecurityConfigProperties securityProperties;
    /**
     * 用户登录
     *
     * @param args 登录参数
     * @return R<?>
     * @author Clover You
     * @date 2023/6/20 12:00
     */
    @SneakyThrows
    @Log(name = "用户登录")
    @Operation(summary = "用户登录")
    @PostMapping("/mobile-phone-login")
    public R<String> login(@RequestBody @Valid UserMobilePhoneLoginRequest args) {
        log.info("登录参数 {}", args);
        // 通过手机号进行登录
        var user = userService.loginByPhone(args.phone().trim(), args.verifyCode().trim());
        ThrowUtils.throwIf(user == null, ErrorCode.SYSTEM_ERROR); // 未知原因错误了

        // 生成 JWT
        var jwtPayload = new JWTPayload();

        user.setUserPassword(null);
        var objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_SELF_REFERENCES_AS_NULL, false);

        // 将用户信息脱敏存储到 jwt
        jwtPayload.setSubject(objectMapper.writeValueAsString(user));
        jwtPayload.setExpiresAt(
            Date.from(LocalDateTime.now()
                .plusSeconds(securityProperties.getExpires())
                .toInstant(ZoneOffset.of("+8"))
            )
        );
        jwtPayload.setIssuedAt(
            Date.from(LocalDateTime
                .now()
                .toInstant(ZoneOffset.of("+8"))
            )
        );

        var jwtToken = JWTUtil.createToken(jwtPayload.getClaimsJson(), securityProperties.getSecret().getBytes());

        var authToken = new UsernamePasswordAuthenticationToken(user.getUserId(), null, new ArrayList<>(0));
        var securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authToken);

        return R.ok(jwtToken);
    }

    /**
     * 用户注册
     *
     * @return R<?>
     * @author Clover You
     * @date 2023/6/20 22:12
     */
    @Log(name = "user register")
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public R<Integer> register(@RequestBody @Valid UserMobileRegisterRequest request) {
        return R.ok(
            userService.register(request.phone().trim(), request.password().trim(), request.verifyCode().trim())
        );
    }

    /**
     * 退出当前用户登录状态
     *
     * @return R<?>
     * @author Clover You
     * @date 2023/6/25 21:10
     */
    @Log(name = "logout")
    @Operation(summary = "退出当前用户登录状态")
    @PostMapping("/logout")
    public R<?> logout() {
        return R.ok();
    }

    @PostMapping("/test")
    public R<?> test() {
        return R.ok();
    }
}
