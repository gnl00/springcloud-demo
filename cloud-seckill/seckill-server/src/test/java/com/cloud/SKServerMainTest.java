package com.cloud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO
 *
 * @author lgn
 * @since 2022/2/12 15:42
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SKServerMainTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test() {
        System.out.println(123);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void setGoodsStock() {
        redisTemplate.opsForValue().set("sk:goods:stock", 100);
    }

    @Test
    public void getGoodsStock() {
        RAtomicLong atomicLong = redissonClient.getAtomicLong("sk:goods:stock");
        System.out.println(atomicLong.getAndDecrement());
    }

}
