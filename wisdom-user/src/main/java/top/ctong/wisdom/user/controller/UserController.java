package top.ctong.wisdom.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ctong.wisdom.common.ErrorCode;
import top.ctong.wisdom.common.exception.ThrowUtils;
import top.ctong.wisdom.common.log.Log;
import top.ctong.wisdom.common.model.dto.user.GetUserInfoByUsernameResponse;
import top.ctong.wisdom.common.model.dto.user.UserInfoResponse;
import top.ctong.wisdom.common.model.entity.User;
import top.ctong.wisdom.common.utils.R;
import top.ctong.wisdom.user.service.UserService;

import java.security.Principal;

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
 * 用户前端控制器
 * </p>
 *
 * @author Clover
 * @date 2023-06-26 17:41
 */
@RestController
@Tag(description = "用户前端控制器", name = "user-controller")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取当前登录用户的信息
     *
     * @return UserInfoResponse
     * @author Clover You
     * @date 2023/6/26 17:48
     */
    @GetMapping("/user-info")
    @Log(name = "获取当前登录用户信息")
    @Operation(summary = "获取当前登录用户信息")
    public R<UserInfoResponse> getUserInfo(Principal principal) {
        var userId = principal.getName();

        User record = userService.getById(Long.valueOf(userId));

        return R.ok(new UserInfoResponse(
            record.getUserId(),
            record.getUserName(),
            record.getPhone(),
            record.getUserAccount(),
            record.getBanned(),
            record.getAvatar(),
            record.getCreateAt(),
            record.getUpdateAt()
        ));
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return R<GetUserInfoByUsernameResponse>
     * @author Clover You
     * @date 2023/7/13 13:37
     */
    @Operation(summary = "根据用户名获取用户信息")
    @Log(name = "根据用户名获取用户信息")
    @GetMapping("/get-userinfo-by-username")
    public R<GetUserInfoByUsernameResponse> getUserInfoByUsername(
        @RequestParam("username") @Parameter(description = "用户名", required = true) Long username
    ) {
        var user = userService.getById(username);
        ThrowUtils.throwIf(user == null, ErrorCode.PARAMS_ERROR, "用户不存在");

        return R.ok(new GetUserInfoByUsernameResponse(
            user.getUserId(),
            user.getUserName(),
            user.getPhone(),
            user.getUserAccount(),
            user.getBanned(),
            user.getAvatar(),
            user.getCreateAt(),
            user.getUpdateAt()
        ));
    }

}