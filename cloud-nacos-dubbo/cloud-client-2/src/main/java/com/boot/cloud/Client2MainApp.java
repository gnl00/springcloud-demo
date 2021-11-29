package com.boot.cloud;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Client2MainApp
 *
 * @author lgn
 */

@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication
public class Client2MainApp {
    public static void main(String[] args) {
        SpringApplication.run(Client2MainApp.class, args);
    }

    @LoadBalanced // 负载均衡配置，若是调用的服务提供者有多个，此注解为必须
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
