package top.ctong.wisdom.core.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
 * security 自定义配置
 * </p>
 *
 * @author Clover
 * @date 2023-06-29 10:45
 */
@Data
@ConfigurationProperties(prefix = "wisdom.security")
public class SecurityConfigProperties {
    /**
     * 验证类型
     * @Default: Bearer
     */
    private String authorizationType = "Bearer";

    /**
     * 验证请求头名称
     * @Default: Authorization
     */
    private String headerName = "Authorization";

    /**
     * 签名生成密钥
     * @Default: wisdom
     */
    private String secret = "2cdc4c5a3d2541378b13fa8a206d393d";

    /**
     * token 过期时间
     * @Default: 1800s
     */
    private Integer expires = 1800;

}
