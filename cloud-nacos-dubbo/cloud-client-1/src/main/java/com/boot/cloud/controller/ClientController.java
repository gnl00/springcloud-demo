package com.boot.cloud.controller;

import com.boot.cloud.common.enumm.StatusCode;
import com.boot.cloud.common.resp.Result;
import com.boot.cloud.user.domain.query.UserQuery;
import com.boot.cloud.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * ClientController
 *
 * @author lgn
 */

@RestController
@RequestMapping("/client")
public class ClientController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${server-url.cloud-provider}")
    private String serverUrl;

    @Value("${config.info}")
    private String configInfo;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/msg")
    private String getMsg() {
        String path = "/pro/msg";
        ResponseEntity<String> response = restTemplate.getForEntity(serverUrl + path, String.class);
        return response.getBody();
    }

    @GetMapping("/config")
    private String getConfigInfo() {
        return configInfo + " " + serverPort;
    }

    @DubboReference(version = "${dubbo-ver.service.user}", check = false)
    private UserService userService;

    @GetMapping("/save")
    public Result save(UserQuery query) {
        Integer result = userService.save(query);
        return new Result(result.equals(1), StatusCode.SUCCESS_CODE, null);
    }

}
