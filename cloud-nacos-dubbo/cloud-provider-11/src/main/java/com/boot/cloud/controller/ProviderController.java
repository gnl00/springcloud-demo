package com.boot.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProviderController
 *
 * @author lgn
 */

@RestController
@RequestMapping("/pro")
public class ProviderController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/msg")
    public String getMsg() {
        String msg = "This is msg return from: " + serverPort;
        return msg;
    }

    @GetMapping("/config")
    public String getConfigInfo() {
        return configInfo + " " + serverPort;
    }

}
