package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SecKillClientMain
 *
 * @author lgn
 * @since 2022/2/14 11:36
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SKClientMain {
    public static void main(String[] args) {
        SpringApplication.run(SKClientMain.class, args);
    }
}
