package com.cloud.service;

import org.redisson.api.RAtomicLong;

/**
 * SecKillService
 *
 * @author lgn
 * @since 2022/2/12 15:48
 */

public interface SecKillService {

    /**
     * 获取商品库存
     * @return Long
     */
    Long getStock();

    /**
     * 执行秒杀请求
     * @return String
     */
    String doSecKill();
}
