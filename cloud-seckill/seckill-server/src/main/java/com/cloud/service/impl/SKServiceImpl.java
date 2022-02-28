package com.cloud.service.impl;

import com.cloud.service.SKService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    private String topic = "tp_seckill";

    private Long duration_time = 2000L;

    private Integer delayLevel = 16;

    @Autowired
    private RedissonClient redissonClient;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

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

                    // 发送消息到 MQ 进行下单操作
                    // messageDelayLevel = 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h 从1级开始，可自定义
                    // 16级表示30 min，30 min 之后查看 订单表中是否有包含 goodsId 的商品信息，若是没有则释放库存
                    rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(goodsId).build(), duration_time, delayLevel);

                    return true;
                } else {
                    log.info("{} 商品库存不足", ThreadName);
                    return false;
                }
            } catch (MessagingException e) {
                log.error("RocketMQ 消息发送失败: {}", e.getMessage());
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
