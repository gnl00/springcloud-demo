package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * SKServerMain
 * <p>
 * <p> 流程
 * <p> 0、提前进行秒杀商品预热，商品缓存到 redis，过期时间设置为比秒杀结束时间稍晚
 * <p> 1、请求在 nginx 进行反向代理和负载均衡
 * <p> 2、请求在网关层进行限流、负载均衡
 * <p> 3、请求在客户端层进行校验（验证码、黑名单）与无库存时快速失败
 * <p> 4、请求在服务提供层负载均衡，此时不重要的服务使用 Hystrix 进行服务降级，将资源倾向秒杀服务
 * <p> 5、经过以上几个步骤，服务器的流量压力应该有所减小。请求到达应用层，借助 MQ 异步生成订单
 * <p>
 * <p> 需要考虑的问题
 * <p> 1、Redis key 的设计。同一个时间可能有多款商品参与秒杀活动，所以需要加上商品Id来区分
 *     商品预热信息 sk:goods:info:{goodsId}
 *     商品库存信息 sk:goods:stock:{goodsId}
 * <p> 2、秒杀地址隐藏
 * <p>
 * <p> 服务
 * <p> 1、seckill-server 作为秒杀服务的提供者。负责秒杀数据预热，处理秒杀请求（秒杀请求分发到 MQ 处理）
 * <p> 2、seckill-client 作为秒杀服务的客户端。负责初步处理秒杀请求（筛选、过滤黑名单用户和验证码未通过的请求）。client 本地维护一个商品库存 map，用于库存不足时返回快速失败
 * <p> 3、seckill-order 作为秒杀服务的订单处理类
 * <p>
 * <p> 启动
 * <p> idea 设置允许多实例运行，vm option 中加入 -Dserver.port=XXXX 启动多个实例
 * <p>
 * <p> 测试 1
 * <p> Jmeter，user=5000 Ramp-up Period（in seconds）=0 loop=3
 * <p> Ramp-up Period（in seconds）说明
 * <p> 1）决定多长时间启动所有线程。如果使用 10 个线程，ramp-up period 是 100 秒，那么 JMeter 用 100 秒使所有 10 个线程启动并运行。
 * 每个线程会在上一个线程启动后 10 秒（100/10）启动。Ramp-up 需要要充足长以避免在启动测试时有一个太大的工作负载，并且要充足小以至于最后一个线程在第一个完成前启动。
 * 一般设置 ramp-up = 线程数启动，并上下调整到所需的。
 * <p> 2）用于告知 JMeter 要在多长时间内建立全部的线程。默认值是 0。如果未指定 ramp-up period ，也就是说 ramp-up period 为零， JMeter 将立即建立所有线程。
 * 假设 ramp-up period 设置成 T 秒， 全部线程数设置成 N 个， JMeter 将每隔 T/N 秒建立一个线程。
 * <p> 3）Ramp-Up Period(in-seconds) 代表隔多长时间执行，0 代表同时并发
 * <p>
 * <p> Bug 1 Redissson 连接超时异常
 * <p> 测试用例：jmeter 1000 user 0 Ramp-up Period 3 loop
 * <p> 错误日志：Exception occured. Channel
 * <p> 原因：版本（3.11.2） Redission 内存溢出
 * <p> 解决办法：seckill-server 1、升级 Redisson 版本至 3.16.x；2、配置文件中 redis 延长 max-wait 和 timeout；2、服务做负载均衡
 * <p> 分析：可能是瞬时并发进来的时候的，最开始的请求没有拿到锁，一直在等待，直到超时报错
 * <p>
 * <p> Bug 2 feign 连接超时异常
 * <p> 原因同 Bug 1
 * <p> 解决办法：seckill-client 延长 feign 查询等待时间
 * <p>
 * ribbon:
 *   ConnnectTimeout: 10000
 *   ReadTimeout: 10000
 * <p>
 *
 * <p> 测试 2
 * <p> Jmeter，user=30000 Ramp-up Period（in seconds）=0 loop=3
 *
 * <p> Bug 2 nginx 502 no live upstreams while connecting to upstream，随着并发量上升，调用秒杀接口一直返回 502 ，nginx 已经发现不到存活的后端服务
 * <p> 原因：nginx 没有使用 http1.1 长连接导致。使用 tcpdump 抓取发往 sk.test.com 的请求源端口是否改变，如果每一笔都变则为短连接；否则是长连接。
 * nginx 连接后端服务时，使用 upstream 的方式，并且设置 keepalive 可以建立长连接，减少创建连接的消耗，提升效率。
 * <p> 解决办法：在 nginx 的 upstream 模块中添加 keepalive 配置。
 * <p> 参考
 * 1、https://www.cnblogs.com/sfnz/p/14715678.html
 * 2、https://www.jianshu.com/p/5d7e40efddc9
 * 3、https://www.cnblogs.com/zjfjava/p/10909087.html
 * 4、长连接、短连接扩展 https://www.cnblogs.com/gotodsp/p/6366163.html
 * <p>
 * upstream sk.test.com {
 *     server localhost:8888;
 *     server localhost:8899;
 *
 *     keepalive 256;
 * }
 * <p>
 * server {
 *     ...
 *     location sk.test.com {
 *         proxy_pass http://sk.test.com;
 *     }
 * }
 * <p>
 * <p> 测试 3
 * <p> Jmeter，user=50000 Ramp-up Period（in seconds）=0 loop=3
 * <p> <strong> nginx 作为反向代理服务器可以实现负载均衡，同时也可以作为静态文件服务器，它的特点就是并发支持大，单机可同时支持3万并发。</strong>
 *     随着并发量的提高，单机的 nginx 开始支撑不住（可支撑数和 nginx 的宿主机配置正相关）
 *
 *
 *
 * <p> 优化思路
 * <p> 1、Nginx 集群
 * <p> 2、秒杀服务集群
 * <p> 3、Redis 集群
 *
 * <p>
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
