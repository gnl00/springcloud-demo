package com.cloud.service.impl;

import com.cloud.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * SecKillServiceImpl
 *
 * @author lgn
 * @since 2022/2/12 15:49
 */

@Slf4j
@Service
public class SecKillServiceImpl implements SecKillService {

    @Value("${seckill.goods.stock-key}")
    private String stockKey;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 从 redis 缓存获取 RAtomicLong 类型商品库存
     */
    public RAtomicLong getStockR() {
        return redissonClient.getAtomicLong(stockKey);
    }

    @Override
    public Long getStock() {
        long stock = getStockR().get();
        return stock;
    }

    @Override
    public String doSecKill() {
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

        return "操作成功 ";
    }
}
