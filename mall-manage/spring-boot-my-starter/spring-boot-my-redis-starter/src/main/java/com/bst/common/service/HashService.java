package com.bst.common.service;

import com.alibaba.fastjson.JSON;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/11/1 13:51 2018 11
 */
public interface HashService extends AbstractRedisService {


    default Long hincr(String key,String field) {
        try {

            Long stoc = jedisCluster().hincrBy(key, field, 1);
            return stoc;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("hincr  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return -1L;
    }

    default Long hincrBy(String key,String field ,Integer number) {
        try {
            Long  lrange1 = jedisCluster().hincrBy(key, field, number);;
            return lrange1;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("hincrBy  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return -1L;
    }




    default Map<String, String> hgetAll(@NonNull String key ) {
        String result = "";
        if (log().isInfoEnabled()) {
            log().info("key is  {}", key);
        }
        try {
            Map<String, String> stringStringMap = jedisCluster().hgetAll(key);
            return stringStringMap;
        } catch (Exception e) {
            log().error("hget获取redis键值异常:key=" + key + " cause:" + e);
        }
        return Collections.EMPTY_MAP;
    }





    /**
     * -----------------------------------------
     * ms     %     Task name
     * -----------------------------------------
     * 00051  035%  hset
     * 00096  065%  hget
     *
     * @param key
     * @param hash
     * @param value
     * @return
     */
    default Long hset(String key, String hash, Object value) {
        return hset(key, hash, value, -1);
    }

    default Long hset(@NonNull String key, @NonNull String hash, @NonNull Object value, int expiretime) {
        Long result = -1L;
        try {
            result = jedisCluster().hset(key, hash, value instanceof String ? (String) value : JSON.toJSONString(value));
            log().info("hset设置redis键值成功:key=" + key + " hash=" + hash + " value=" + value);
            return result;
        } catch (Exception e) {
            log().error("hset设置redis键值异常:key=" + key + " hash=" + hash + " value=" + value + " cause:" + e);
        }
        return result;
    }

    default Optional<String> hget(@NonNull String key, @NonNull String hash) {
        String result = "";
        if (log().isInfoEnabled()) {
            log().info("key is  {}", key);
        }
        try {

            result = jedisCluster().hget(key, hash);
            if (!StringUtils.isBlank(result)) {
                return Optional.ofNullable(result);
            }
        } catch (Exception e) {
            log().error("hget获取redis键值异常:key=" + key + " hash=" + hash + "  cause:" + e);
        }
        return Optional.empty();
    }


    default Long hdecrBy(String key,String field) {
        try {
            Long  lrange1 = jedisCluster().hincrBy(key,field,-1);
            return lrange1;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("hdecr  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return -1L;
    }

    default Long hdecrBy(String key,String field, Integer number) {
        try {
            int value = 0 - number;
            Long  lrange1 = jedisCluster().hincrBy(key,field, value);
            return lrange1;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("hdecrBy  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return -1L;
    }

}
