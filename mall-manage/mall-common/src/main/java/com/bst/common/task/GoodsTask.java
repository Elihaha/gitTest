package com.bst.common.task;

import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.entity.order.CourierCompany;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.common.mapper.goods.GoodsSpuMapper;
import com.bst.common.mapper.order.CourierCompanyMapper;
import com.bst.common.utils.RedisParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.List;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/25 9:37 2018 09
 */
@Component
public class GoodsTask implements CommandLineRunner {

    @Autowired
    CourierCompanyMapper courierCompany;

    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    GoodsSpuMapper goodsSpuMapper;
    @Autowired
    JedisCluster jedisCluster;

    @Override
    public void run(String... args) throws Exception {
        List<CourierCompany> list = courierCompany.list(new HashMap<>());
        list.forEach(courierCompany1 -> {
            jedisCluster.setnx(RedisParam.COURIER_COMPANY+courierCompany1.getCode(),courierCompany1.getName());
            jedisCluster.setnx(RedisParam.COURIER_COMPANY+courierCompany1.getName(),courierCompany1.getCode());

        });

        List<GoodsSku> goodsSkus = goodsSkuMapper.selectGoodsSkuAll();
        goodsSkus.forEach(goodsSku -> {
            jedisCluster.setnx(RedisParam.KEY_GOODSSKU + goodsSku.getSkuNo(), String.valueOf(goodsSku.getStock()));
        });
        List<GoodsSpu> goodsSpus = goodsSpuMapper.selectGoodsSpuAll();
        goodsSpus.forEach(goodsSpu -> {
            jedisCluster.setnx(RedisParam.KEY_GOODSSPU + goodsSpu.getSpuNo(), String.valueOf(goodsSpu.getTotalStock()));
        });
    }
}
