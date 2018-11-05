package com.bst.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/27 14:04 2018 09
 */
@Component
public class RedisLock {

    @Autowired
    JedisCluster jedisCluster;
    private final static String NX = "NX";
    private final static String PX = "PX";

    public boolean lock(String key,String value) {
       if("OK".equals(jedisCluster.set(key, value, NX, PX, 3 * 60 * 1000))){
           return true;
       }
        return false;
    }


}
