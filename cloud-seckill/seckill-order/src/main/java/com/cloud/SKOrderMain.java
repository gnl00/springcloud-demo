package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * SKOrderMain
 *
 * @author lgn
 * @since 2022/2/28 14:03
 */

@EnableDiscoveryClient
@SpringBootApplication
public class SKOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(SKOrderMain.class, args);
    }
}
