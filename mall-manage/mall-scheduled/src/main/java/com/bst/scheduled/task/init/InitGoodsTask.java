package com.bst.scheduled.task.init;

import com.alibaba.fastjson.JSON;
import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.entity.goods.GoodsSpecValueEntity;
import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.common.mapper.goods.GoodsSpuMapper;
import com.bst.common.modle.goods.GoodsRedisInfo;
import com.bst.common.modle.goods.GoodsSkuAndImg;
import com.bst.common.modle.goods.GoodsSkuAndImgCache;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pengzhen
 * @version 1.0.5
 * @email pengzhen
 * @date 2018/9/25 9:37 2018 09
 */
@Component
public class InitGoodsTask implements  Runnable {

    @Autowired
    RedisParam redisParam;


    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    GoodsSpuMapper goodsSpuMapper;
    @Autowired
    JedisClusterUtils jedisCluster;

    @Autowired
    GoodsRedisService goodsRedisService;

    private String keyGoodssku;
    private String keyGoodsspu;
    private String keyGoodsskuAll;



    @Override
    public void run() {
        keyGoodssku = redisParam.getKeyGoodssku();
        keyGoodsspu = redisParam.getKeyGoodsspu();
        keyGoodsskuAll = redisParam.getKeyGoodsskuAll();
        if (StringUtils.isNoneBlank(keyGoodssku, keyGoodsspu, keyGoodsskuAll)) {
            Long shopId =null;
            String spuNo =null;
            goodsRedisService.setGoodsSkuAndImgs(spuNo,shopId);
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            goodsRedisService.setGoodsRedisInfos(shopId, spuNo);

        } else {
            new RuntimeException("请配置数据");
        }
    }





}
