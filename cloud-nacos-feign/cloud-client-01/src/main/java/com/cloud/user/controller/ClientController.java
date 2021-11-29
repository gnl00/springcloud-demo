package com.cloud.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClientController
 *
 * @author lgn
 * @date 2021-11-29 14:38
 */

@RefreshScope
@RestController
@RequestMapping("/cli")
public class ClientController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config")
    public String getConfigInfo() {
        return configInfo + " " + serverPort;
    }

}
