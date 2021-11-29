package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Provider01Main
 *
 * @author lgn
 * @date 2021-11-29 13:34
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Provider01Main {
    public static void main(String[] args) {
        SpringApplication.run(Provider01Main.class, args);
    }
}
