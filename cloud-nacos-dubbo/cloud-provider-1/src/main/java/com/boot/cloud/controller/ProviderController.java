package com.boot.cloud.controller;

import com.boot.cloud.common.enumm.StatusCode;
import com.boot.cloud.common.resp.Result;
import com.boot.cloud.user.domain.query.UserQuery;
import com.boot.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

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

    @Resource
    private UserService userService;

    @GetMapping("/save")
    public Result save(@RequestBody UserQuery query) {

        System.out.println("===> ProviderController#save");

        String uuid = UUID.randomUUID().toString();
        query.setUserAccount(uuid);

        Integer result = userService.save(query);

        return new Result(result.equals(1), StatusCode.SUCCESS_CODE, query);
    }

}
