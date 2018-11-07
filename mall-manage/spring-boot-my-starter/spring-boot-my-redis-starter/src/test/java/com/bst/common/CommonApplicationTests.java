package com.bst.common;

import com.bst.common.service.HashService;
import com.bst.common.utils.JedisClusterUtils;
import com.sun.scenario.effect.Offset;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CommonApplicationTests {

    @Test
    public void contextLoads() {
//        JedisClusterUtils
//        String[] serverArray = "120.79.164.233:7501,120.79.164.233:7502,120.79.164.233:7503,120.79.164.233:7504,120.79.164.233:7505,120.79.164.233:7506".split(",");
//        Set<HostAndPort> nodes = new HashSet<>();
//
//        for (String ipPort : serverArray) {
//            String[] ipPortPair = ipPort.split(":");
//            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
//        }
//
//
//        JedisCluster jedisCluster=new JedisCluster(nodes,1000, 1000, 1, "qwsedh./asd0sad;as,.asdgay213y1ad89we", new GenericObjectPoolConfig());
//        jedisCluster.zadd("{tetsasa}:1234", LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli(),"aaa");
//        jedisCluster.zadd("{tetsasa}:1234",LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli(),"aaa1");
//        jedisCluster.zadd("{tetsasa}:1236",LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli(),"aaa");
//        jedisCluster.zadd("{tetsasa}:1237",LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli(),"aaa");
//
//
//        final JedisClusterUtils jedisClusterUtils = new JedisClusterUtils(jedisCluster);
//        jedisClusterUtils.keysStream("{tetsasa}",entries -> {
//            entries.stream().forEach(stringStringEntry -> {});
//        });
//        final ScanResult<Map.Entry<String, String>> hscan = jedisCluster.hscan("{tetsasa}:1234", "0");
//        System.out.println(hscan);


        Stream<Integer> stream = Stream.of(96, 852, 432, 121);

        Integer lengthSum = stream.reduce(0, // 初始值 // (1)

                (sum, str) -> sum+str, // 累加器 // (2)

                (a, b) -> a+b); // 部分和拼接器，并行执行时才会用到 // (3)

// int lengthSum = stream.mapToInt(str -> str.length()).sum();

        System.out.println(lengthSum);
    }

}
