package com.tangerine.specter.redis;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class BloomFilterDemo {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        //构造Redisson
        RedissonClient redissonClient = Redisson.create(config);
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("bloom");
        //初始化布隆过滤器：预计元素为100万个，误判率为1%
        bloomFilter.tryInit(1000000L, 0.01);
        bloomFilter.add("1");
        //判断编号是否在布隆过滤器中
        System.out.println(bloomFilter.contains("1"));
        System.out.println(bloomFilter.contains("233"));
    }
}
