package com.boot.cloud;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * MainApplication
 * @author lgn
 */

@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication
public class Provider2MainApp {
    public static void main(String[] args) {
        SpringApplication.run(Provider2MainApp.class, args);
    }
}
