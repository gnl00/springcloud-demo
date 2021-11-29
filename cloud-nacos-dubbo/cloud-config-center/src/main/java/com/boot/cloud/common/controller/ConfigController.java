package com.boot.cloud.common.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConfigController
 * @author lgn
 */

@RequestMapping("/config")
@RestController
@RefreshScope // 开启 nacos 配置文件动态刷新
public class ConfigController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/info")
    public String getConfigInfo() {
        return StringUtils.isEmpty(configInfo) ? "Default_Info" : configInfo;
    }

}
