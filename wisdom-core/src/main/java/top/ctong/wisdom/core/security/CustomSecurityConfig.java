package top.ctong.wisdom.core.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import top.ctong.wisdom.core.security.filter.DefaultJwtAuthenticationFilter;
import top.ctong.wisdom.core.security.filter.SecurityBeforeFilter;

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
 * SpringSecurity 过滤
 * </p>
 *
 * @author Clover
 * @date 2023-06-25 16:21
 */
public interface CustomSecurityConfig {

    default String[] permitAll() {
        return new String[0];
    }

    default String[] postPermitAll() {
        return new String[0];
    }

    default String[] getPermitAll() {
        return new String[0];
    }

    default SecurityBeforeFilter loginFilter(SecurityConfigProperties properties, UserDetailsService userDetailsService) {
        return new DefaultJwtAuthenticationFilter(properties, userDetailsService);
    }

}
