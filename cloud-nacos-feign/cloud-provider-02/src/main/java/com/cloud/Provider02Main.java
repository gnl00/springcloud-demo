package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Provider02Main
 *
 * @author lgn
 * @date 2021-11-29 14:07
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Provider02Main {
    public static void main(String[] args) {
        SpringApplication.run(Provider02Main.class, args);
    }
}
