package com.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * GlobalAuthFilter 全局权限处理 Filter
 *
 * @author lgn
 * @date 2021-11-30 10:54
 */

@Slf4j
@Component
public class GlobalAuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 1、检查访问的路径是否需要权限（可将请求和对应的权限等级存到数据库中，或者Controller 方法添加注解反射获取）
        // 不需要则直接放行，需要则进行第二步
        // 2、获取请求头中的 token
        // 3、根据token 信息调用 AuthClient 检查当前操作用户是否具有访问改路径的权限，有则放行，没有则直接 setComplete

        log.info("test_gateway GlobalAuthFilter#filter");

        HttpHeaders headers = exchange.getRequest().getHeaders();
        headers.get("Authorization");
        System.out.println(headers);

        MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();

        System.out.println(cookies);

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
