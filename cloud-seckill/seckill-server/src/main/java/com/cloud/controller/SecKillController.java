package com.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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

    @Value("${seckill.goods.stock-key}")
    private String stockKey;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 从 redis 缓存获取 RAtomicLong 类型商品库存
     */
    public RAtomicLong getStockR() {
        return redissonClient.getAtomicLong(stockKey);
    }

    @GetMapping("/stock")
    public Long getStock() {
        long stock = getStockR().get();
        return stock;
    }

    private int count = 0;

    @GetMapping("/doSecKill")
    public String doSecKill() {

        log.info("request 请求进来 {}", count++);

        RLock lock = redissonClient.getLock("sk_lock");

        String ThreadName = Thread.currentThread().getName();

        if (getStock() > 0) {
            // redisson 加锁
            lock.lock();
            try {
                Long stock = getStock();
                if (stock > 0) {
                    RAtomicLong stockR = getStockR();
                    log.info("{} 获得商品: {}", ThreadName, stockR.getAndDecrement());
                } else {
                    log.info("{} 商品库存不足", ThreadName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        } else {
            log.info("{} 商品库存不足", ThreadName);
        }

        return "操作成功 " + serverPort;
    }

}
