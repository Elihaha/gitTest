package com.bst.user.permission.shiro;

import com.bst.user.permission.Constants;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author gan
 * @desc: 使用第三方内存数据库Redis作为mybatis二级缓存
 */
public class RedisCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private static JedisConnectionFactory jedisConnectionFactory;

    private final String id;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static byte[] hKeyByteArr;

    static {
        try {
            hKeyByteArr = Constants.MYBATIS_CACHE_HASH_KEY.getBytes(Constants.CHARACTER);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.debug("MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public void clear() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Object getObject(Object key) {
        Object result = null;
        RedisClusterConnection connection = null;
        try {
            connection = jedisConnectionFactory.getClusterConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            byte[] bytes = connection.hGet(hKeyByteArr,serializer.serialize(key));
            //反序列化结果
            result = serializer.deserialize(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    @Override
    public int getSize() {
        int result = 0;
        RedisClusterConnection connection = null;
        try {
            connection = jedisConnectionFactory.getClusterConnection();
            result = Math.toIntExact(connection.hLen(hKeyByteArr));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisClusterConnection connection = null;
        try {
            connection = jedisConnectionFactory.getClusterConnection();
            //使用jdk序列化对象
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            connection.hSet(hKeyByteArr, serializer.serialize(key), serializer.serialize(value));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Object removeObject(Object key) {
        RedisClusterConnection connection = null;
        Object result = null;
        try {
            connection = jedisConnectionFactory.getClusterConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result = connection.hDel(hKeyByteArr, serializer.serialize(key));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }

}
