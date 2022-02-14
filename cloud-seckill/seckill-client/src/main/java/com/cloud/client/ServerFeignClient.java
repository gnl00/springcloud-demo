package com.cloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ServerFeignClient
 *
 * @author lgn
 * @since 2022/2/14 11:41
 */

@FeignClient(name = "seckill-server")
@RequestMapping("/sk")
@ResponseBody
public interface ServerFeignClient {

    @GetMapping("/stock")
    Long getStock();

    @GetMapping("/doSecKill")
    String doSecKill();

}
