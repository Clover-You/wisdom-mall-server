package top.ctong.wisdom.thirdparty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ctong.wisdom.core.security.CustomSecurityConfig;

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
 * 安全配置
 * </p>
 *
 * @author Clover
 * @date 2023-07-12 14:50
 */
@Configuration
public class WebConfig {

    @Bean
    public CustomSecurityConfig customSecurityConfig() {
        return new CustomSecurityConfig() {
            @Override
            public String[] getPermitAll() {
                return new String[]{
                    "/mobile-phone-verify-code/send-login-code",
                    "/wechat/advanced"
                };


            }

            @Override
            public String[] postPermitAll() {
                return new String[]{
                    "/wechat/advanced"
                };
            }
        };
    }

}
