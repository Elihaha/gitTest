package com.bst.user.permission.shiro;

import com.bst.common.redis.MyRedisAutoConfiguration;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gan
 * @desc: Redis连接配置
 */
@Configuration
public class RedisCacheConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * @param
     * @desc: Redis 连接参数配置
     * @return:
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        RedisProperties.Pool pool = redisProperties.getJedis().getPool();
        jedisPoolConfig.setMaxTotal(pool.getMaxActive());
        jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
        jedisPoolConfig.setMinIdle(pool.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(pool.getMaxWait().toMillis());
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

    /**
     * @desc: Redis集群配置
     * @return:
     */
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        Set<RedisNode> nodes = new HashSet<>();
        List<String> nodesList = redisProperties.getCluster().getNodes();
        for (String nodeInfo : nodesList) {
            String[] hostAndPort = nodeInfo.split(":");
            String host = hostAndPort[0];
            String port = hostAndPort[1];
            RedisClusterNode node = new RedisClusterNode(host, Integer.parseInt(port));
            nodes.add(node);
        }
        redisClusterConfiguration.setClusterNodes(nodes);
        return redisClusterConfiguration;
    }

    /**
     * @param
     * @desc: Redis连接池配置
     * @return:
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = jedisPoolConfig();
        RedisClusterConfiguration redisClusterConfiguration = redisClusterConfiguration();
        final String password = redisProperties.getPassword();
        if(!StringUtils.isBlank(password)){
            redisClusterConfiguration.setPassword(RedisPassword.of(password));
        }

        return new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
    }

    /**
     * @param jedisConnectionFactory
     * @desc: 静态注入中间类Redis连接池
     * @return:
     */
    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


}
