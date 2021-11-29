package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Client01Main
 *
 * @author lgn
 * @date 2021-11-29 14:36
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Client01Main {
    public static void main(String[] args) {
        SpringApplication.run(Client01Main.class, args);
    }
}
