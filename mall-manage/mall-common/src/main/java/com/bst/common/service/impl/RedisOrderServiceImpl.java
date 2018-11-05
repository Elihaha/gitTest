package com.bst.common.service.impl;

import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.Calendar;

@Order(2)
@Component
public class RedisOrderServiceImpl {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RedisOrderServiceImpl.class);

//    @Value("redisKey.config.order_timeout-delay")
//    private String order_timeout_delay_key;

    @Autowired
    private RedisParam redisParam;

    @Autowired
    private JedisClusterUtils jedisCluster;

    //产生订单
    public void productionDelayMessage(String orderNo){
        try {
            //延迟2h
            Calendar cal1 = Calendar.getInstance();
            cal1.add(Calendar.SECOND, 7200);
            double score = (cal1.getTimeInMillis() / 1000);
            jedisCluster.zadd(redisParam.getOrderTimeoutDelayKey(), score,orderNo);
            System.out.println(System.currentTimeMillis()+"ms:redis生成了一个订单任务：订单ID为"+orderNo);
        }catch (Exception e){
            LOGGER.error(">>>>>>订单超时管理RedisOrderServiceImpl，异常：", e);
        }
    }
}
