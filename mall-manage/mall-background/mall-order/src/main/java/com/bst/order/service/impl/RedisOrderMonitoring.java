package com.bst.order.service.impl;

import com.bst.common.entity.order.OrderDetail;
import com.bst.common.entity.order.Orders;
import com.bst.common.entity.order.Status;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.service.RedisOrderService;
import com.bst.order.service.OrderDetailService;
import com.bst.order.service.OrdersManageService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Tuple;

import java.util.Calendar;
import java.util.Set;

@Primary
@Order(2)
@Component
public class RedisOrderMonitoring extends Thread implements RedisOrderService, ApplicationRunner {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RedisOrderMonitoring.class);

    @Autowired
    private JedisCluster jedisCluster;
    @Autowired
    private OrdersManageService ordersService;
    @Autowired
    private GoodsRedisService goodsRedisService;
    @Autowired
    private OrderDetailService orderDetailService;

    //产生订单
    public void productionDelayMessage(String orderNo){
        try {
            //延迟10秒
            Calendar cal1 = Calendar.getInstance();
            cal1.add(Calendar.SECOND, 7200);
            int score = (int) (cal1.getTimeInMillis() / 1000);
            jedisCluster.zadd("OrderId", score,orderNo);
            System.out.println(System.currentTimeMillis()+"ms:redis生成了一个订单任务：订单ID为"+orderNo);
        }catch (Exception e){
            LOGGER.error(">>>>>>订单超时管理RedisOrderServiceImpl，异常：", e);
        }


    }

    //消费者，取订单
    public void consumerDelayMessage(){
        while(true){
            Set<Tuple> items = jedisCluster.zrangeWithScores("OrderId", 0, 1);
            if(items == null || items.isEmpty()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;
            }
            try {
                int  score = (int) ((Tuple)items.toArray()[0]).getScore();
                Calendar cal = Calendar.getInstance();
                int nowSecond = (int) (cal.getTimeInMillis() / 1000);
                if(nowSecond >= score){
                    String orderNo = ((Tuple)items.toArray()[0]).getElement();
                    Long num = jedisCluster.zrem("OrderId", orderNo);
                    if( num != null && num>0){
                        System.out.println(System.currentTimeMillis()+"订单过期"+orderNo);
                        //修改库存
                        Orders orders = ordersService.selectByOrderNo(orderNo);
                        //商品数量
                        if (orders!=null&&orders.getPayStatus()== (byte) 1){
                            int goodsCount =orders.getGoodsCount();
                            OrderDetail orderDetail = orderDetailService.queryOrderDetail(orders.getId());
                            goodsRedisService.addCountBySkuNO(orderDetail.getSkuNo(),goodsCount);
                            goodsRedisService.addCountBySpuNO(orderDetail.getSpuNo(),goodsCount);
                            Orders ordersNew =new Orders();
                            ordersNew.setId(orders.getId());
                            ordersNew.setOrderStatus(Status.invaild);
                            ordersNew.setPayStatus((byte)3);
                            ordersService.updateByPrimaryKeySelective(ordersNew);
                        }
                    }
                }
            }catch (Exception e){
                LOGGER.error(">>>>>>订单超时管理消费RedisOrderServiceImpl，异常：", e);
            }
        }

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        start();
    }

    @Override
    public void run() {
        consumerDelayMessage();
    }
}
