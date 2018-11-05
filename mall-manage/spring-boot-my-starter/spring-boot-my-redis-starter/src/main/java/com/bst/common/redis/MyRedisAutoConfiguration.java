package com.bst.common.redis;

import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configurable
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties({RedisParam.class, RedisProperties.class})
public class MyRedisAutoConfiguration {


    @Autowired
    private  RedisParam redisParam;

    @Autowired
    private  RedisProperties redisProperties;


    @Bean("redisClient")
    @ConditionalOnMissingBean(RedisClient.class)
//    @ConditionalOnBean(RedisProperties.class)
    RedisClient redisClient(){
        return new RedisClient(redisProperties);
    }


    @Bean
    @ConditionalOnMissingBean(JedisClusterUtils.class)
    @ConditionalOnBean(RedisClient.class)
    JedisClusterUtils jedisClusterUtils(){
        final RedisClient client = redisClient();
        return new JedisClusterUtils(client.getJedisCluster());
    }
}
