package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * AuthMain
 *
 * @author lgn
 * @date 2021-11-30 11:34
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AuthMain {
    public static void main(String[] args) {
        SpringApplication.run(AuthMain.class, args);
    }
}
