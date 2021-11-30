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

        log.info("hhh_test im here~");

        return builder
                .routes()
                .route("id-route-1", r -> r.path("/baidu").uri("https://www.baidu.com"))
                .build();
    }

}
