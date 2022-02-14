package com.cloud.controller;

import com.cloud.client.ServerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SKClientController
 *
 * @author lgn
 * @since 2022/2/14 11:39
 */

@RestController
@RequestMapping("/client")
public class SKClientController {

    @Resource
    private ServerFeignClient client;

    @GetMapping("/test")
    public String test() {
        return "client test";
    }

    @GetMapping("/stock")
    public Long stock() {
        return client.getStock();
    }

    @GetMapping("/sk")
    public String secKill() {
        return client.doSecKill();
    }

}
