package com.cloud.controller;

import com.cloud.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * SecKillController
 *
 * @author lgn
 * @since 2022/2/12 15:39
 */

@Slf4j
@RestController
@RequestMapping("/sk")
public class SecKillController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private SecKillService secKill;

    private int count = 0;

    @GetMapping("/")
    public String clearRequest() {
        count = 0;
        return "操作成功 " + count;
    }

    @GetMapping("/stock")
    public Long getStock() {
        return secKill.getStock();
    }

    @GetMapping("/doSecKill")
    public String doSecKill() {

        log.info("request 请求进来 {}", count++);



        return secKill.doSecKill() + serverPort;
    }

}
