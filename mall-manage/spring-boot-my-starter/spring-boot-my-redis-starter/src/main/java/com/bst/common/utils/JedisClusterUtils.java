package com.bst.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bst.common.service.HashService;
import com.bst.common.service.StringService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/10/17 10:26 2018 10
 */
@Slf4j
public class JedisClusterUtils implements StringService, HashService {

    private JedisCluster jedisCluster;


    public JedisClusterUtils() {
    }

    public JedisClusterUtils(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }


    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }






    private <T> void UpExpiretime(String key, int expiretime, Optional<T> result) {
        if (result.isPresent()) {
            if (expiretime > 0) {
                jedisCluster().expire(key, expiretime);
            }
        }
    }





    public Long sAdd(@NonNull String key, @NonNull String... arg) {
       return sAdd(key,-1 ,arg);
    };

    public Long sAdd(@NonNull String key, int expiretime, @NonNull String... arg) {
        int arglen = arg.length;
        Set<Object> collect = Stream.of(arg).filter(Objects::nonNull).collect(Collectors.toSet());
        long count = collect.size();
        if (arglen != count) throw new RuntimeException(" redis list  增加的数组有null值");

        Long result = -1L;
        if (log().isInfoEnabled()) {
            log().info(" sAdd  key is  {}", key);
        }
        try {

            result = jedisCluster().sadd(key, collect.stream().map(Object::toString).toArray(String[]::new));
            UpExpiretime(key, expiretime, Optional.ofNullable(result));
            log().error("sadd设置redis键值成功:key=" + key + " value=" + arg);
            return result;
        } catch (Exception e) {
            log().error("sadd设置redis键值异常:key=" + key + " value=" + arg + " cause:" + e);
        }
        return result;
    }

    public Set<String> setGet(String key, int expiretime) {
        return smembers(key, expiretime);
    }

    public Set<String> smembers(@NonNull String key, int expiretime) {
        try {

            Long llen = jedisCluster().scard(key);
            if (llen == 0) throw new RuntimeException(key + "值为null");
            Set<String> lrange1 = jedisCluster().smembers(key);
            UpExpiretime(key, expiretime, Optional.ofNullable(lrange1));
            return lrange1;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("smembers  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return Collections.EMPTY_SET;
    }

    /**
     * 删除key
     */
    public Long delkeyObject(@NonNull String key) {
        try {
            return jedisCluster().del(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除key
     */
    public void expire(@NonNull String key, int expiretime) {
        try {

            jedisCluster().expire(key, expiretime * 1000);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public Boolean existsObject(String key) {
        try {

            return jedisCluster().exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<String> keys(@NonNull String key) {
        return keys(key, -1);
    }

    public Set<String> keys(@NonNull String key, int expiretime) {
        try {

            Set<String> keys = HashSet.class.newInstance();
            String cursor = "-1";
            do {
                cursor = scan("0", key + "*", c -> {
                    keys.addAll(c);
                });
            } while (!cursor.equals("0"));
            if (keys.size() == 0) throw new RuntimeException(key + "值为null");
            UpExpiretime(key, expiretime, Optional.ofNullable(keys));
            return keys;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("keys  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return Collections.EMPTY_SET;
    }


    public Boolean keysStream(@NonNull String key, Consumer<List<String>> consumer) {
        try {

            String cursor = "-1";
            do {
                cursor = scan("0", key + "*", consumer);
            } while (!cursor.equals("0"));
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("keysStream  获取redis键值异常:key=%s cause:%s", key, e));
            return false;
        }
        return true;
    }

    public Boolean hkeysStream(@NonNull String key, Consumer<List<Map.Entry<String, String>>> consumer) {
        try {

            String cursor = "-1";
            do {
//                cursor = hscan("0", key , consumer);
            } while (!cursor.equals("0"));
        } catch (Exception e) {
            log().error(String.format("hkeysStream  获取redis键值异常:key=%s cause:%s", key, e),e);
            return false;
        }
        return true;
    }


    public Set<Tuple> zrangeWithScores(String key, int start, int end) {
        try {
            Long llen = jedisCluster().zcard(key);
            if (llen == 0){
                log().debug("这个key 没有 值");
                return Collections.EMPTY_SET;
            }
            Set<Tuple> lrange1 = jedisCluster().zrangeWithScores(key,start,end);
            return lrange1;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("zrangeWithScores  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return Collections.EMPTY_SET;
    }

    public Long zrem(String key, String orderNo) {
        try {
            Long  lrange1 = jedisCluster().zrem(key,orderNo);
            return lrange1;
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("zrem  获取redis键值异常:key=%s cause:%s", key, e));
        }
        return -1L;
    }


    /**
     * 查询
     *
     * @param cursor
     * @param match
     * @param consumer
     * @return
     */
    private String scan(@NonNull String cursor, @NonNull String match, @NonNull Consumer<List<String>> consumer) {
        ScanParams scanParams = new ScanParams();
        scanParams.count(1000);
        scanParams.match(match + "*");
        ScanResult<String> scan = jedisCluster().scan(cursor, scanParams);
        List<String> result = scan.getResult();
        log().debug("scan  match  {}", result.size());
        consumer.accept(result.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList()));
        String stringCursor = scan.getStringCursor();
        return stringCursor;
    }

//    private String hscan(@NonNull String cursor, @NonNull String match, @NonNull Consumer<List<Map.Entry<String, String>>> consumer) {
//        ScanParams scanParams = new ScanParams();
//        scanParams.count(1000);
//        scanParams.match(match + "*");
//        final ScanResult<Map.Entry<String, String>> hscan = jedisCluster().hscan(cursor, com.bst.common.entity.goods.GoodsSpu.STOCK);
//        List<Map.Entry<String, String>> result = hscan.getResult();
//        log().debug("scan  match  {}", result.size());
//        consumer.accept(result.stream().filter(Objects::nonNull).collect(Collectors.toList()));
//        String stringCursor = hscan.getStringCursor();
//        return stringCursor;
//    }


    public void zadd(String key, Double score, String value) {
        try {
             jedisCluster().zadd(key, score,value);
            log().info("zadd设置redis键值成功:key=" + key + " value=" + value);
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("zadd value  设置redis键值异常:key=%s cause:%s", key, e));
        }
    }

    public void zadd(String key, Long score, String value) {
        try {
             jedisCluster().zadd(key, score,value);
            log().info("zadd设置redis键值成功:key=" + key + " value=" + value);
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("zadd value  设置redis键值异常:key=%s cause:%s", key, e));
        }
    }

    public void zadd(String key, Map<String, Double> map) {
        try {
            jedisCluster().zadd(key, map);
        } catch (Exception e) {
            e.fillInStackTrace();
            log().error(String.format("zadd  map 设置redis键值异常:key=%s cause:%s", key, e));
        }
    }



    public  Long  zcard(String key){
//        System.out.println("zcard     "+key);
        return jedisCluster().zcard(key);
    }


    public  String  type(String key){
        System.out.println(jedisCluster().type(key)+" ------------ "+key);
        return jedisCluster().type(key);
    }





    @Override
    public Logger log() {
        return log;
    }

    @Override
    public JedisCluster jedisCluster() {
        return jedisCluster;
    }
}
