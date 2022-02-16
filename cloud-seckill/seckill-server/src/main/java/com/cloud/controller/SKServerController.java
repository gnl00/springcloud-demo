package com.cloud.controller;

import com.cloud.service.SKService;
import io.undertow.util.CopyOnWriteMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * SecKillController
 *
 * @author lgn
 * @since 2022/2/12 15:39
 */

@Slf4j
@RestController
@RequestMapping("/sk")
public class SKServerController {

    @Autowired
    private SKService secKill;

    private int count = 0;

    @GetMapping("/clearCount")
    public String clearRequest() {
        count = 0;
        return "操作成功【统计清除】" + count;
    }

    @GetMapping("/stock/{goodsId}")
    public Long getStock(@PathVariable("goodsId") Long goodsId) {
        return secKill.getStock(goodsId);
    }

    @GetMapping("/doSecKill/{goodsId}")
    public Boolean doSecKill(@PathVariable("goodsId") Long goodsId) {
        log.info("请求进来 {}", count++);
        return secKill.doSecKill(goodsId);
    }

}
