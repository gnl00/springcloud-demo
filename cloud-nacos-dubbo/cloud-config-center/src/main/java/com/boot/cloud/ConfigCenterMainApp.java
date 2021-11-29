package com.boot.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ConfigCenterMainApp
 * @author lgn
 */

@EnableDiscoveryClient
@SpringBootApplication
public class ConfigCenterMainApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMainApp.class, args);
    }
}
