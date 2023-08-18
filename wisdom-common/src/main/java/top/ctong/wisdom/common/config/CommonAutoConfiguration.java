package top.ctong.wisdom.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import top.ctong.wisdom.common.http.service.LogServiceClient;
import top.ctong.wisdom.common.http.service.UserServiceClient;

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
 * 公共模块自动配置类
 * </p>
 *
 * @author Clover
 * @date 2023-06-20 16:07
 */
@AutoConfiguration
public class CommonAutoConfiguration {

    @Bean
    public WebClient webClient(
        @Autowired(required = false) LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction
    ) {
        if (loadBalancedExchangeFilterFunction == null) {
           return WebClient.builder().build();
        }
        return WebClient.builder().filter(loadBalancedExchangeFilterFunction).build();
    }

    @Bean
    public LogServiceClient logServiceClient(WebClient webClient) {
        var factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return factory.createClient(LogServiceClient.class);
    }

    @Bean
    public UserServiceClient userServiceClient(WebClient webClient) {
        var factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return factory.createClient(UserServiceClient.class);
    }

}
