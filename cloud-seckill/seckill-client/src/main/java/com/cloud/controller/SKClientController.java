package com.cloud.controller;

import com.cloud.client.SKFeignClient;
import io.undertow.util.CopyOnWriteMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * SKClientController
 *
 * @author lgn
 * @since 2022/2/14 11:39
 */

@RestController
@RequestMapping("/client")
public class SKClientController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private SKFeignClient client;

    /**
     * 本地商品库存缓存，秒杀进行到某个请求发现商品已无库存，则设置 map.put(商品缓存 id, false)
     */
    private Map<Long, Boolean> localStockCache = new CopyOnWriteMap<>();

    @GetMapping("/test")
    public String test() {
        return "client connect test " + serverPort;
    }

    @GetMapping("/resetCache/{goodsId}")
    public String resetLocalCache(@PathVariable("goodsId") Long goodsId) {
        localStockCache.remove(goodsId);
        return "操作成功【重置本地库存】 " + serverPort;
    }

    @GetMapping("/stock/{goodsId}")
    public Long stock(@PathVariable("goodsId") Long goodsId) {
        return client.getStock(goodsId);
    }

    @GetMapping("/sk/{goodsId}")
    public String secKill(@PathVariable("goodsId") Long goodsId) {
        if (Objects.isNull(localStockCache.get(goodsId))) {
            if (!client.doSecKill(goodsId)) {
                localStockCache.put(goodsId, false);
                return "操作成功【库存不足】 " + serverPort;
            } else {
                return "操作成功 " + serverPort;
            }
        } else {
            return "操作成功【库存不足】 " + serverPort;
        }
    }

}
