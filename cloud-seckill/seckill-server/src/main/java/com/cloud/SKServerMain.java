package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SKServerMain
 *
 * 开始
 * 0、提前进行秒杀商品预热，商品缓存到 redis，过期时间设置为比秒杀结束时间稍晚
 * 1、请求在 nginx 层进行负载均衡
 * 2、请求在网关层进行分流（负载均衡）与校验（验证码、黑名单）
 * 3、请求在应用层负载均衡，此时不重要的服务使用 Hystrix 进行服务降级，将资源倾向秒杀服务
 * 4、经过以上几个步骤，服务器的流量压力应该有所减小。请求到达应用层，借助 MQ 异步生成订单
 *
 * 测试
 * Jmeter user 20 Ramp-up period (seconds) 100 loop count 10
 * 在此基础下 tomcat 表现不是很好，经常卡住；更换成 undertow 之后稍微好些
 *
 * @author lgn
 * @since 2022/2/12 15:21
 */

@SpringBootApplication
public class SKServerMain {
    public static void main(String[] args) {
        SpringApplication.run(SKServerMain.class, args);
    }
}
