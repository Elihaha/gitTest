package com.bst.common.redis;


import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import redis.clients.jedis.*;
import redis.clients.util.JedisClusterCRC16;

import java.util.*;

/**
 * @author zhang zhiye
 *
 */
public class RedisClient {
    private static JedisPool jedisPool = null;

    private static JedisPoolConfig config = new JedisPoolConfig();

    private JedisCluster jedisCluster;

    /**
     * redis节点集合
     */
    private static Map<String, JedisPool> nodeMap;

    /**
     * redis槽点集合
     */
    private static TreeMap<Long, String> slotHostMap;

    private RedisProperties redisProperties;

    RedisClient(RedisProperties properties){
        this.redisProperties = properties;
        if (jedisPool == null) {
            String host = redisProperties.getHost();
            int port = redisProperties.getPort();
            RedisProperties.Pool pool = redisProperties.getJedis().getPool();
            config.setMaxTotal(pool.getMaxActive());
            config.setMaxIdle(pool.getMaxIdle());
            config.setMinIdle(pool.getMinIdle());
            config.setMaxWaitMillis(pool.getMaxWait().toMillis());
            config.setTestOnBorrow(true);
            int timeOut = (int) redisProperties.getTimeout().toMillis();
            jedisPool = new JedisPool(config, host, port,timeOut);
        }
        initJedisCluster();
    }

    public static JedisPool getPool() {
        return jedisPool;
    }

    /**
     * 获取Jedi源
     * @return jedis
     */
    public Jedis getJedis(){
        return getPool().getResource();
    }

    private JedisCluster initJedisCluster() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        List<String> nodeAddresses = redisProperties.getCluster().getNodes();
        for (String nodeAddress : nodeAddresses) {
            String[] hostAndPort =  nodeAddress.split(":");
            if (hostAndPort.length < 2) { continue; }
            jedisClusterNodes.add(new HostAndPort(hostAndPort[0],Integer.parseInt(hostAndPort[1])));
        }
        jedisCluster = new JedisCluster(jedisClusterNodes,config);
        nodeMap = jedisCluster.getClusterNodes();
        String anyHost = nodeMap.keySet().iterator().next();
        slotHostMap = getSlotHostMap(anyHost);
        return jedisCluster;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    private static TreeMap<Long, String> getSlotHostMap(String anyHostAndPortStr) {
        TreeMap<Long, String> tree = new TreeMap<Long, String>();
        String parts[] = anyHostAndPortStr.split(":");
        HostAndPort anyHostAndPort = new HostAndPort(parts[0], Integer.parseInt(parts[1]));
        try{
            Jedis jedis = new Jedis(anyHostAndPort.getHost(), anyHostAndPort.getPort());
            List<Object> list = jedis.clusterSlots();
            for (Object object : list) {
                List<Object> list1 = (List<Object>) object;
                List<Object> master = (List<Object>) list1.get(2);
                String hostAndPort = new String((byte[]) master.get(0)) + ":" + master.get(1);
                tree.put((Long) list1.get(0), hostAndPort);
                tree.put((Long) list1.get(1), hostAndPort);
            }
            jedis.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return tree;
    }

    /**
     * 通过key获取到这个key所对应的Jedis对象
     * @param key redis key
     * @return redis.clients.jedis.Jedis
     */
    public static Jedis getJedisByKey(String key) {
        //获取槽号
        int slot = JedisClusterCRC16.getSlot(key);
        //获取到对应的Jedis对象
        Map.Entry<Long, String> entry = slotHostMap.lowerEntry((long) slot);
        return nodeMap.get(entry.getValue()).getResource();
    }

}