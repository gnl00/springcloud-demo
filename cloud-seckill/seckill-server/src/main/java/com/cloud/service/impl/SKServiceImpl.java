package com.cloud.service.impl;

import com.cloud.service.SKService;
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
public class SKServiceImpl implements SKService {

    @Value("${seckill.goods.stock-key}")
    private String stockKey;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 从 redis 缓存获取 RAtomicLong 类型商品库存
     * @param goodsId 商品 Id
     */
    public RAtomicLong getStockR(Long goodsId) {
        return redissonClient.getAtomicLong(stockKey + goodsId);
    }

    @Override
    public Long getStock(Long goodsId) {
        long stock = getStockR(goodsId).get();
        return stock;
    }

    @Override
    public Boolean doSecKill(Long goodsId) {
        RLock lock = redissonClient.getLock("rds_sk_lock_" + goodsId);

        String ThreadName = Thread.currentThread().getName();

        if (getStock(goodsId) > 0) {
            // redisson 加锁
            lock.lock();
            try {
                if (getStock(goodsId) > 0) {
                    RAtomicLong stockR = getStockR(goodsId);
                    log.info("{} 获得商品: {}", ThreadName, stockR.getAndDecrement());
                    return true;
                } else {
                    log.info("{} 商品库存不足", ThreadName);
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            log.info("{} 商品库存不足", ThreadName);
            return false;
        }
        return false;
    }
}
