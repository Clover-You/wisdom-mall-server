package top.ctong.wisdom.common.model.dto.user.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
 * 手机号登录参数
 * </p>
 *
 * @author Clover
 * @date 2023-06-20 11:57
 */
@Schema(description = "手机号登录参数")
public record UserMobilePhoneLoginRequest(
    @NotBlank(message = "账号不能为空")
    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED)
    String phone,
    @NotBlank(message = "验证码不能为空")
    @Size(max = 6, min = 6, message = "请输入6位验证码")
    @Schema(description = "验证码", requiredMode = Schema.RequiredMode.REQUIRED)
    String verifyCode
) { }