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
 * 用户注册
 * </p>
 *
 * @author Clover
 * @date 2023-06-20 22:13
 */
@Schema(description = "用户手机号注册参数")
public record UserMobileRegisterRequest(
    @NotBlank(message = "手机号不能为空")
    @Schema(description = "手机号", maxLength = 11, minLength = 11, requiredMode = Schema.RequiredMode.REQUIRED)
    String phone,

    @NotBlank(message = "密码不能为空")
    @Size(max = 20, min = 6, message = "密码限制6-20位")
    @Schema(description = "密码", maxLength = 20, minLength = 6, requiredMode = Schema.RequiredMode.REQUIRED)
    String password,

    @NotBlank(message = "请输入验证码")
    @Size(max = 6, min = 6, message = "请输入6位验证码")
    @Schema(description = "验证码", minLength = 6, maxLength = 6, requiredMode = Schema.RequiredMode.REQUIRED)
    String verifyCode
) {}