package top.ctong.wisdom.core.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.ctong.wisdom.common.model.entity.User;

import java.util.ArrayList;
import java.util.Collection;

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
 * spring security 用户信息
 * </p>
 *
 * @author Clover
 * @date 2023-06-25 21:40
 */
public record UserDetailsProvider(User user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // todo 获取用户所有权限信息，这个权限一般是在每次请求的时候，通过 security 拦截器去设置的一个临时的权限列表
        //  client -> cache -> DB
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(user.getUserId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getBanned();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // todo 登录状态是否过期了，预计 30 分钟未操作就将其过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 如果是通过邮箱进行注册，那么可能是邮箱未认证账户或者其它注册方式，认证之后启用
        return true;
    }
}