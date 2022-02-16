package com.cloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SKFeignClient
 *
 * @author lgn
 * @since 2022/2/14 11:41
 */

@FeignClient(name = "seckill-server")
@RequestMapping("/sk")
@ResponseBody
public interface SKFeignClient {

    /**
     * 获取商品库存
     * @param goodsId
     * @return java.lang.Long
     */
    @GetMapping("/stock/{goodsId}")
    Long getStock(@PathVariable("goodsId") Long goodsId);

    /**
     * 执行秒杀请求
     * @param goodsId
     * @return java.lang.Boolean
     */
    @GetMapping("/doSecKill/{goodsId}")
    Boolean doSecKill(@PathVariable("goodsId") Long goodsId);

}
