package com.bst.mallh5.service.order.impl;

import com.alibaba.fastjson.JSON;
import com.bst.common.mapper.order.PostageConfigDao;
import com.bst.common.modle.Result;
import com.bst.common.modle.goods.GoodsRedisInfo;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import com.bst.mallh5.service.order.PostageConfigHService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisCluster;

import java.math.BigDecimal;


@Service("postageConfigHService")
@Slf4j
@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class PostageConfigHServiceImpl implements PostageConfigHService {
    @Autowired
    private PostageConfigDao postageConfigDao;

    //    @Value("${redisKey.config.mallGoodsSpuBaseInfo}")
//    private String goodsSpuBaseInfoKey;
    @Autowired
    RedisParam redisParam;


    @Autowired
    private JedisClusterUtils jedisCluster;

    @Autowired
    private GoodsRedisService goodsRedisService;

    @Override
    public Result queryByProvince(String name, Long goodsId) {
        try {
            //商品信息从redis中获取
            String key = redisParam.getGoodsSpuBaseInfoKey().replaceAll("_", ":") + ":" + goodsId;
            String json = jedisCluster.get(key);
            if (StringUtils.isBlank(json)) {
                BigDecimal po = postageConfigDao.queryByGoodsIdAndAddress(name, goodsId);
                return Result.ok(po == null ? BigDecimal.valueOf(0) : po);
            }
            GoodsRedisInfo goodsSpu = JSON.parseObject(json, GoodsRedisInfo.class);
            BigDecimal currentPostageMerchant = goodsRedisService.getCurrentPostageMerchant(name, goodsSpu.getShopId());
            return Result.ok(currentPostageMerchant);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }


}
