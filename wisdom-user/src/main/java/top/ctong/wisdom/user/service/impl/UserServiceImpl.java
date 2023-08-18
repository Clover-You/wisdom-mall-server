package top.ctong.wisdom.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ctong.wisdom.common.ErrorCode;
import top.ctong.wisdom.common.exception.ThrowUtils;
import top.ctong.wisdom.common.model.entity.User;
import top.ctong.wisdom.core.security.UserDetailsProvider;
import top.ctong.wisdom.user.mapper.UserMapper;
import top.ctong.wisdom.user.service.UserService;

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
 * 用户业务逻辑处理
 * </p>
 *
 * @author Clover
 * @date 2023-06-20 22:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

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
    @Override
    public Integer register(String phone, String password, String verifyCode) {
        Integer account = queryAvailableAccount();
        // 防止同一个账号并发新增
        synchronized (account.toString().intern()) {

            synchronized (phone.trim().intern()) {
                var findPhoneWrapper = new LambdaQueryWrapper<User>();
                findPhoneWrapper.eq(User::getPhone, phone.trim());
                ThrowUtils.throwIf(baseMapper.exists(findPhoneWrapper), ErrorCode.PARAMS_ERROR, "该手机号已被注册");
            }

            // todo 密码加密
            // 新增用户
            var user = User.builder()
                .phone(phone)
                .userAccount(account)
                .userName("username")
                .userPassword(password)
                .build();
            save(user);
        }
        return account;
    }

    /**
     * 在数据库中获取一个可用的账号
     *
     * @return Integer
     * @author Clover You
     * @date 2023/6/20 22:39
     */
    @Override
    public Integer queryAvailableAccount() {
        return baseMapper.queryAvailableAccount();
    }

    /**
     * 通过手机号进行登录
     *
     * @param phone      手机号
     * @param verifyCode 验证码
     * @return boolean
     * @author Clover You
     * @date 2023/6/25 21:16
     */
    @Override
    public User loginByPhone(String phone, String verifyCode) {
        var queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getPhone, phone);

        var userRecord = baseMapper.selectOne(queryWrapper);

        ThrowUtils.throwIf(userRecord == null, ErrorCode.PARAMS_ERROR, "手机号不存在");

        // todo 校验验证码是否正确，如果验证码校验失败则返回 false 表示登录失败验证码错误
        return userRecord;
    }

    /**
     * 根据用户信息包装 user details
     *
     * @param user 用户信息
     * @return UserDetailsProvider
     * @author Clover You
     * @date 2023/6/25 21:47
     */
    @Override
    public UserDetailsProvider wrapperDetails(User user) {
        return new UserDetailsProvider(user);
    }
}
