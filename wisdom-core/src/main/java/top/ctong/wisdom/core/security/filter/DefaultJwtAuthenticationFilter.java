package top.ctong.wisdom.core.security.filter;

import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.ctong.wisdom.common.model.entity.User;
import top.ctong.wisdom.common.utils.StringUtils;
import top.ctong.wisdom.core.security.SecurityConfigProperties;
import top.ctong.wisdom.core.security.UserDetailsProvider;

import java.io.IOException;

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
 * 登录校验拦截器
 * </p>
 *
 * @author Clover
 * @date 2023-06-25 21:57
 */
public class DefaultJwtAuthenticationFilter extends SecurityBeforeFilter {

    /**
     * 日志输出
     */
    private final Logger log = LoggerFactory.getLogger(DefaultJwtAuthenticationFilter.class);

    public DefaultJwtAuthenticationFilter(SecurityConfigProperties properties, UserDetailsService userDetailsService) {
        this.properties = properties;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest req,
        HttpServletResponse resp,
        FilterChain filterChain
    ) throws ServletException, IOException {
        var type = properties.getAuthorizationType();
        var headerName = properties.getHeaderName();
        var secret = properties.getSecret();

        log.info("AuthorizationType={},AuthorizationName={}", type, headerName);

        var header = req.getHeader(headerName);
        // 检查是否存在对应请求头并且是对应的类型
        if (StringUtils.isBlank(header) || !header.startsWith(type + " ")) {
            filterChain.doFilter(req, resp);
            return;
        }

        var token = header.substring(type.length() + 1);

        // 校验 jwt
        var authVerify = JWTUtil.verify(token, secret.getBytes());
        if (!authVerify) {
            filterChain.doFilter(req, resp);
            return;
        }

        var jwt = JWTUtil.parseToken(token);
        JWTPayload payload = jwt.getPayload();

        var userJson = (String) payload.getClaim(JWTPayload.SUBJECT);
        var securityContext = SecurityContextHolder.getContext();
        var authentication = securityContext.getAuthentication();

        if (authentication == null && userJson != null) {
            // 无状态 JWT（注意，使用 jwt 会导致jwt无法被动作废）
            var objectMapper = new ObjectMapper();
            var user = objectMapper.readValue(userJson, User.class);
            var userDetails = new UserDetailsProvider(user);

            var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            securityContext.setAuthentication(authToken);
        }

        filterChain.doFilter(req, resp);
    }

}
