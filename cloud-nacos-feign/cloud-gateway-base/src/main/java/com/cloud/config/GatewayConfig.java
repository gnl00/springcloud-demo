package com.cloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GatewayConfig
 *
 * @author lgn
 * @date 2021-11-29 19:33
 */

@Slf4j
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        // 进行路由映射
        return builder
                .routes()
                .route("path_route_guonei", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .build();
    }

}
