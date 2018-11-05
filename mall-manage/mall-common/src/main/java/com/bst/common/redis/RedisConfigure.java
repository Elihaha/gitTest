package com.bst.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
 * redis配置
 * @author zhang
 * 2018/5/16 17:10
 */
@Configuration
public class RedisConfigure {

    /*@Bean(name = "redisProperties")
    @ConfigurationProperties(prefix = "spring.redis")
    public RedisProperties redisProperties() {
        System.out.println("-------------------- redisProperties init ---------------------");
        return new RedisProperties();
    }*/

    @Autowired
    @Bean(name = "redisClient")
    public RedisClient redisClient(RedisProperties redisProperties) {
        return new RedisClient(redisProperties);
    }

    @Bean(name = "jedisCluster")
    public JedisCluster jedisCluster(@Qualifier("redisClient") RedisClient redisClient) {
        return redisClient.getJedisCluster();
    }
}
