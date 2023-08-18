package top.ctong.wisdom.common.model.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 根据用户名获取用户信息接口的响应数据
 * </p>
 *
 * @author Clover
 * @date 2023-07-13 13:33
 */
@Schema(description = "根据用户名获取用户信息接口的响应数据")
public record GetUserInfoByUsernameResponse(
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date createAt,

    @Schema(description = "账户信息修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date updateAt
) {}