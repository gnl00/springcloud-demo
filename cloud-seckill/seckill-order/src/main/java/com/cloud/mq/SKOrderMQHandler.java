package com.cloud.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * SKOrderMQHandler
 *
 * @author lgn
 * @since 2022/2/28 14:04
 */

@Component
@RocketMQMessageListener(consumerGroup = "gp_seckill", topic = "tp_seckill")
public class SKOrderMQHandler implements RocketMQListener<Long> {

    @Override
    public void onMessage(Long goodsId) {

        // 接收到延迟消息，查询数据库订单表是否存在 goodsId

        // 不存在，释放库存 ==> redis 中的库存 + 1
        if (!hasGoodsId(goodsId)) {

            // 可以采用 redis 集群，并且将需要更新库存的那台 redis 的权重设置为最低
            // 更新的时候专门更新权重最低的那台服务器

        }

        // 存在，do nothing

    }

    private Boolean hasGoodsId(Long goodsId) {
        return false;
    }

}
