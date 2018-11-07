package com.bst.user.permission.shop.service.impl;

import com.bst.common.entity.shop.ShopInfo;
import com.bst.common.mapper.shop.ShopInfoMapper;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import com.bst.user.permission.shop.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 这个类是缓存预热的实现类
 * @Author: Xia Zhengxin
 * @Date: 2018/11/6 15:59
 * @Version 1.0
 */
@Service
public class ICacheServiceImpl implements ICacheService {
    @Autowired
    private ShopInfoMapper shopInfoMapper;


    @Autowired
    private JedisClusterUtils jedisClusterUtils;

    @Autowired
    private RedisParam redisParam;

    /**
     * 这个方法来初始化缓存
     */
    @Override
    public void initShopInfoCache() {
        List<ShopInfo> list = shopInfoMapper.selectAllInfo();
        for (ShopInfo info:list) {
            String key = redisParam.getGoodsShopBaseInfoKey().replaceAll("_", ":") + ":" + info.getId();
            jedisClusterUtils.set(key, info);
        }
    }
}
