package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ProviderBaseMain
 *
 * @author lgn
 * @date 2021-11-29 17:01
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderBaseMain {
    public static void main(String[] args) {
        SpringApplication.run(ProviderBaseMain.class, args);
    }
}
