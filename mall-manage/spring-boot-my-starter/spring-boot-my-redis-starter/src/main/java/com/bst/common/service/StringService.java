package com.bst.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import redis.clients.jedis.JedisCluster;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/11/1 13:43 2018 11
 */

public   interface StringService extends AbstractRedisService {

    /**
     * 获取redis键值-object
     *
     * @param key
     * @return
     */
    default String get(@NonNull String key) {
        try {
            if (log().isInfoEnabled()) {
                log().info(" get key is  {}", key);
            }
            String bytes = jedisCluster().get(key);
            if (!StringUtils.isEmpty(bytes)) {
                return bytes;
            }
        } catch (Exception e) {
            log().error("getObject获取redis键值异常:key=" + key + " cause:" + e);
        }
        return null;
    }



    /**
     * 获取redis键值-object
     *
     * @param key
     * @return
     */
    default  <T> T get(@NonNull String key, @NonNull Class<T> t) {
        try {
            if (log().isInfoEnabled()) {
                log().info(" get  key is  {}", key);
            }
            String bytes = jedisCluster().get(key);
            if (!StringUtils.isEmpty(bytes)) {
                return JSONObject.parseObject(bytes, t);
            }
        } catch (Exception e) {
            log().error("get T 获取redis键值异常:key=" + key + " cause:" + e);
            return null;
        }
        return null;
    }


    /**
     * 设置redis键值-object
     *
     * @param key
     * @param value
     * @param
     * @return
     */
    default String set(@NonNull String key, @NonNull Object value) {
        String result = "";
        if (log().isInfoEnabled()) {
            log().info(" set key is  {}", key);
        }
        try {
            result = jedisCluster().set(key, value instanceof String ? (String) value : JSON.toJSONString(value));
            return result;
        } catch (Exception e) {
            log().error("setObject设置redis键值异常:key=" + key + " value=" + value + " cause:" + e);
        }
        return result;
    }

    default void setnx(String key, String value) {
        String result = "";
        if (log().isInfoEnabled()) {
            log().info(" setnx key is  {}", key);
        }
        try {
            jedisCluster().setnx(key, value instanceof String ? (String) value : JSON.toJSONString(value));
        } catch (Exception e) {
            log().error("setObject设置redis键值异常:key=" + key + " value=" + value + " cause:" + e);
        }
    }



    default Long incr(String key) {
        try {
            Long  lrange1 = jedisCluster().incr(key);
            return lrange1;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("incr  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return -1L;
    }

    default Long incrBy(String key, Integer number) {
        try {
            Long  lrange1 = jedisCluster().incrBy(key,number);
            return lrange1;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("incrBy  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return -1L;
    }


    default Long decr(String key) {
        try {
            Long  lrange1 = jedisCluster().decr(key);
            return lrange1;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("decr  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return -1L;
    }

    default Long decrBy(String key, Integer number) {
        try {
            Long  lrange1 = jedisCluster().decrBy(key,number);
            return lrange1;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("decrBy  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return -1L;
    }



}
