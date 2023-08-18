package top.ctong.wisdom.thirdparty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ctong.wisdom.common.ErrorCode;
import top.ctong.wisdom.common.utils.R;
import top.ctong.wisdom.common.constant.redis.RedisVerifyCode;
import top.ctong.wisdom.common.exception.ThrowUtils;
import top.ctong.wisdom.common.utils.StringUtils;

import java.util.Random;

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
 * 手机验证码
 * </p>
 *
 * @author Clover
 * @date 2023-07-03 22:48
 */
@RequestMapping("/mobile-phone-verify-code")
@RestController
@Tag(name = "mobile-phone-verify-code-controller", description = "手机验证码相关前端控制器")
public class MobilePhoneVerifyCodeController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送登录验证码
     *
     * @param phone 手机号
     * @return R<?>
     * @author Clover You
     * @date 2023/7/3 22:52
     */
    @Operation(summary = "发送登录验证码")
    @GetMapping("/send-login-code")
    public R<?> sendLoginCode(@RequestParam("phone") @Parameter(description = "手机号") String phone) {
        ThrowUtils.throwIf(StringUtils.isBlank(phone), ErrorCode.PARAMS_ERROR, "手机号不能为空");
        phone = phone.trim();
        ThrowUtils.throwIf(phone.length() > 11, ErrorCode.PARAMS_ERROR, "手机号错误");

        var random = new Random();

        var code = random.nextInt(100000, 999999);

        stringRedisTemplate.opsForValue().set(
            RedisVerifyCode.MOBILE_PHONE_LOGIN_CODE + ":" + phone,
            Integer.toString(code)
        );

        return R.ok(code);
    }

}
