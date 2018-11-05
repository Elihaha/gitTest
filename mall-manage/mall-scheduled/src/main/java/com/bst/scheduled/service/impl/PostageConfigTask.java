package com.bst.scheduled.service.impl;

import com.bst.common.entity.order.PostageConfigEntity;
import com.bst.common.mapper.order.PostageConfigDao;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/30 9:55 2018 09
 */
//@Component
public class PostageConfigTask    implements Runnable {

    @Autowired
    RedisParam redisParam;

    @Autowired
    PostageConfigDao postageConfigDao;

    @Value("${redisKey.config.postageConfig}")
    private String postageConfig;

    @Autowired
    JedisClusterUtils jedisCluster;

    @Override
    public void run() {
        List<PostageConfigEntity> postageConfigEntities = postageConfigDao.queryList(new HashMap<>());
        postageConfigEntities.stream()
                .collect(Collectors.groupingBy(PostageConfigEntity::getShopId))
                .forEach((aLong, postage) -> {
                    postage.forEach(postageConfig -> {
                        jedisCluster.hset(redisParam.getPostageConfig() + aLong, postageConfig.getProvince(), postageConfig.getPostage().setScale(4, BigDecimal.ROUND_HALF_UP).toString());
                    });

                });
    }
}
