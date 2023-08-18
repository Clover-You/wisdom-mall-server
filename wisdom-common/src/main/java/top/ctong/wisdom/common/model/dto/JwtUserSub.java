package top.ctong.wisdom.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

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
 * 在 jwt 中的用户信息
 * </p>
 *
 * @author Clover
 * @date 2023-06-29 13:43
 */
@Schema(description = "在 jwt 中的用户信息")
public record JwtUserSub(

    @Schema(description = "用户id")
    Long userId,

    @Schema(description = "用户名")
    String userName,

    @Schema(description = "用户绑定的手机号")
    String phone,

    @Schema(description = "账号")
    Integer userAccount,

    @Schema(description = "是否封禁")
    Boolean banned,

    @Schema(description = "头像")
    String avatar,

    @Schema(description = "用户创建时间")
    Date createAt,

    @Schema(description = "账户信息修改时间")
    Date updateAt

) {}
