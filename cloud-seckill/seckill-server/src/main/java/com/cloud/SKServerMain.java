package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SKServerMain
 *
 * <p> 架构
 * <p> 0、提前进行秒杀商品预热，商品缓存到 redis，过期时间设置为比秒杀结束时间稍晚
 * <p> 1、请求在 nginx 层进行负载均衡
 * <p> 2、请求在网关层进行分流（负载均衡）与校验（验证码、黑名单）
 * <p> 3、请求在应用层负载均衡，此时不重要的服务使用 Hystrix 进行服务降级，将资源倾向秒杀服务
 * <p> 4、经过以上几个步骤，服务器的流量压力应该有所减小。请求到达应用层，借助 MQ 异步生成订单
 *
 * <p> 开发步骤
 * <p> 1、seckill-server 作为秒杀服务的提供者。负责秒杀数据预热，处理秒杀请求（秒杀请求分发到 MQ 处理）
 * <p> 2、seckill-client 作为秒杀服务的客户端。负责初步处理秒杀请求（筛选、过滤黑名单用户和验证码未通过的请求）
 * <p> 3、seckill-order 作为秒杀服务的订单处理类
 *
 * <p> 启动
 * <p> idea vm option 中加入 -Dserver.port=XXXX 启动多个服务，做负载均衡
 *
 * <p> 测试
 * <p> 1、Jmeter 做测试， Ramp-up Period（in seconds）说明
 * <p> 1）决定多长时间启动所有线程。如果使用10个线程，ramp-up period是100秒，那么JMeter用100秒使所有10个线程启动并运行。
 * 每个线程会在上一个线程启动后10秒（100/10）启动。Ramp-up需要要充足长以避免在启动测试时有一个太大的工作负载，并且要充足小以至于最后一个线程在第一个完成前启动。
 * 一般设置ramp-up=线程数启动，并上下调整到所需的。
 * <p> 2）用于告知JMeter 要在多长时间内建立全部的线程。默认值是0。如果未指定ramp-up period ，也就是说ramp-up period 为零， JMeter 将立即建立所有线程。
 * 假设ramp-up period 设置成T 秒， 全部线程数设置成N个， JMeter 将每隔T/N秒建立一个线程。
 * <p> 3）Ramp-Up Period(in-seconds) 代表隔多长时间执行，0代表同时并发
 *
 *
 * <p> Bug1
 * <p>
 * <p> 测试用例：jmeter 1000 user 0 Ramp-up Period 3 loop
 * <p> 错误日志：Exception occured. Channel
 * <p> 原因：当前版本（3.11.2） Redission 内存溢出
 * <p> 解决：1、升级 Redisson 版本至 3.16.x；2、配置文件中 redis 延长 max-wait 和 timeout
 * <p> 分析：可能是瞬时并发进来的时候的，最开始的请求没有拿到锁，一直在等待，等到超时
 *
 * <p> 优化思路
 * 1、秒杀服务集群
 * 2、Redis 集群
 *
 * @author lgn
 * @since 2022/2/12 15:21
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SKServerMain {
    public static void main(String[] args) {
        SpringApplication.run(SKServerMain.class, args);
    }
}
