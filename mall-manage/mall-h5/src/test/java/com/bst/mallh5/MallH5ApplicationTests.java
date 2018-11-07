package com.bst.mallh5;

import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.common.mapper.order.OrderChildMapper;
import com.bst.common.mapper.order.OrdersMapper;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallH5Application.class)
public class MallH5ApplicationTests {

    @Autowired
    RedisParam redisParam;
    @Autowired
    OrderChildMapper orderChildMapper;
    @Autowired
    JedisClusterUtils jedisCluster;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    GoodsRedisService goodsRedisService;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;



    @Test
    public void contextLoads() {
       Long OrderId = 15L;

       GoodsSku goodsSku =  goodsSkuMapper.selectSkuBySkuNo("SKU507999864496324608");
       System.out.println(goodsSku);
//        {GOODSSKU}:SPU495638537387900928
//     List<OrderChild>  list = orderChildMapper.selectByMainId(OrderId);
//
//        //Orders order = ordersMapper.selectByPrimaryKey(OrderId);
//     for(OrderChild orderChild : list){
//           System.out.println(orderChild.getId());
//        Integer currentSkuCountBySkuId = goodsService.getCurrentSkuCountBySkuNO(6+"");
//                     System.out.println(jedisCluster.hget(redisParam.getPostageConfig()+1,"sandong"));
//        goodsService.addCountBySkuNO(null);
//        System.out.println(jedisCluster.flushAll());
//        redisLock.lock("test","asdas");

//        System.out.println(JSONArray.toJSONString(goodsRedisService.getAllSpuInfoByShopId(1L)));

    }


//    }

}
