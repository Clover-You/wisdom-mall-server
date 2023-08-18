package top.ctong.wisdom.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ctong.wisdom.common.model.entity.User;
import top.ctong.wisdom.core.security.UserDetailsProvider;

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
 * 用户业务接口
 * </p>
 *
 * @author Clover
 * @date 2023-06-20 22:24
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param phone      手机号
     * @param password   密码
     * @param verifyCode 验证码
     * @return Integer 账号
     * @author Clover You
     * @date 2023/6/20 22:30
     */
    Integer register(String phone, String password, String verifyCode);

    /**
     * 在数据库中获取一个可用的账号
     *
     * @return Integer
     * @author Clover You
     * @date 2023/6/20 22:39
     */
    Integer queryAvailableAccount();

    /**
     * 通过手机号进行登录
     *
     * @param phone      手机号
     * @param verifyCode 验证码
     * @return boolean
     * @author Clover You
     * @date 2023/6/25 21:16
     */
    User loginByPhone(String phone, String verifyCode);

    /**
     * 根据用户信息包装 user details
     *
     * @param user 用户信息
     * @return UserDetailsProvider
     * @author Clover You
     * @date 2023/6/25 21:47
     */
    UserDetailsProvider wrapperDetails(User user);
}
