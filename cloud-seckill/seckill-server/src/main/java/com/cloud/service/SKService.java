package com.cloud.service;

/**
 * SecKillService
 *
 * @author lgn
 * @since 2022/2/12 15:48
 */

public interface SKService {

    /**
     * 获取商品库存
     * @param goodsId 商品Id
     * @return Long
     */
    Long getStock(Long goodsId);

    /**
     * 执行秒杀请求
     * @param goodsId 商品Id
     * @return Boolean
     */
    Boolean doSecKill(Long goodsId);
}
