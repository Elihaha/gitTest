package com.bst.common.service;

import org.slf4j.Logger;
import redis.clients.jedis.JedisCluster;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/11/1 13:47 2018 11
 */
public interface AbstractRedisService {

    public Logger log();

    public JedisCluster jedisCluster();
}
