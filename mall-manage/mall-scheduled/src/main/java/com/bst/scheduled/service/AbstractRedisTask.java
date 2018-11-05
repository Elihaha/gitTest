package com.bst.scheduled.service;

import com.bst.common.modle.order.OrderDetailMonitoringInfo;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import com.bst.order.service.OrderDetailService;
import com.bst.order.service.OrdersManageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Tuple;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;


/**
 * @description 检测redis里面待支付的订单是否过期 并且进行处理
 */
@Slf4j
public abstract class AbstractRedisTask implements  Runnable {

    @Autowired
    protected JedisClusterUtils jedisCluster;

    protected RedisParam redisParam;

    /**
     * 初始化的  数据大小
     */
    protected Long lowSize;
    /**
     * init  休息时间
     */
    protected int initTime = 1000;

//    @Value("redisKey.config.order_timeout-delay")
//    private String ORDER_TIMEOUT_DELAY_KEY;

    //消费者，取订单
    public void consumerDelayMessage() {
        while (true) {
            mySleep(initTime);
            final Long newSize = jedisCluster.zcard(getKey());
            Set<Tuple> items = frequency(newSize);
            if (items == null || items.isEmpty()) {
                mySleep(initTime);
                continue;
            }
            try {
                Mybreak:
                for (Tuple tuple : items) {
                        Long score =  Double.valueOf(tuple.getScore()).longValue();
                        Long nowSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
                        if (nowSecond >= score) {
                            String number = tuple.getElement();
                            Long num = jedisCluster.zrem(getKey(), number);
                            if (num != null && num > 0) {
                                try {
                                    getMessage(number);
                                    logic( number);
                                } catch (Exception e) {
                                    //    出現錯誤 手動  回 滾
                                      jedisCluster.zadd(getKey(),Double.valueOf(tuple.getScore()), number);
                                    e.printStackTrace();
                                }
                            }
                        }else{
                            break Mybreak;
                        }
                }

            } catch (Exception e) {
                log.error(">>>>>>AbstractRedisTask，异常：", e);
            }
        }

    }

    protected abstract void logic(String orderNo);

    /**
     *  提示信息
     */
    protected abstract void getMessage(String orderNo);
    /**
     *  键
     */
    protected abstract String getKey();

    private Set<Tuple> frequency(Long newSize) {
        int start = 0;
        int end = 1;
        final Double run;
        final Double rate;
        if (newSize > lowSize) {
            // 数量增加   减少休息时间
            rate = getRate(newSize, lowSize);
            run = initTime + (initTime * rate);

        } else {
            //  数量减少   增加休息时间
            rate = getRate(lowSize, newSize);
            run = initTime - (initTime * rate);

        }
        end = rate.intValue()+1;
        initTime=run.intValue();
        if (newSize==0) {
            lowSize =1L ;
        }
        return jedisCluster.zrangeWithScores(getKey(), start, end);
    }

    protected double getRate(Long newSize, Long lowSize) {
        if(newSize<1){
            newSize=1L;
        }
        if(lowSize<1){
            lowSize=1L;
        }
        return ((newSize / lowSize) + (newSize % lowSize * 0.01));
    }

    protected void mySleep(int speed) {
        try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public abstract void init();

    @Override
    public void run() {
        consumerDelayMessage();
    }

    @Autowired
    public void setRedisParam(RedisParam redisParam) {
        this.redisParam = redisParam;
        init();
    }

    public abstract void add();
}
