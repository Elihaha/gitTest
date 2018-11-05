package com.bst.scheduled.task.init;

import com.bst.common.entity.order.Orders;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import com.bst.order.service.OrdersManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @param
 * @author WangCheng  pz
 * @version 1.5.0
 * @description：初始化订单到redis
 * @date 2018/10/9 11:40
 * @LastUpdate 2018-10-15 16:59:29
 */
@Component
public class InitOrderToRedis implements Runnable{
    @Autowired
    private JedisClusterUtils jedisCluster;
    @Autowired
    private
    OrdersManageService ordersService;

//    @Value("redisKey.config.order_timeout-delay")
//    private String ORDER_TIMEOUT_DELAY_KEY;


    @Autowired
    private RedisParam redisParam;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run()   {
        //修改订单超时状态
        ordersService.updateOrderStatusTimeOut();
        Long id = 0L;
        //初始化订单状态为1的到redis
        List<Orders> orders = ordersService.initOrderToRedis(id);
        final int size = orders.size();
        if (orders != null) {
            while (size != 0){
                disposeOrderToRedis(orders);
                id = orders.get(size - 1).getId();
            };
        }

    }

    public void disposeOrderToRedis(List<Orders> orders) {
        Map<String, Double> map = orders.stream().collect(Collectors.toMap(Orders::getOrderNo, Orders::getDoubleTimestampNum));
        if (map.isEmpty()) {
            return;
        }
        try {
            jedisCluster.zadd(redisParam.getOrderTimeoutDelayKey(), map);
            /*Set<Tuple> items = jedisCluster.zrangeWithScores(ORDER_TIMEOUT_DELAY_KEY, 0, -1);
            int  score = (int) ((Tuple)items.toArray()[0]).getScore();
            System.out.println(score);
            System.out.println( ((Tuple)items.toArray()[0]).getElement());*/
        } catch (Exception ex) {
            logger.error("add order Delay task into redis error. -> ", ex);
        }
    }
}
