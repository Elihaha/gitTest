package com.bst.user.permission.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.bst.common.entity.shop.ShopInfo;
import com.bst.common.mapper.shop.ShopInfoMapper;
import com.bst.common.entity.shop.QueryShop;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import com.bst.user.permission.shop.service.IShopService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商店服务
 */
@Service
public class ShopService implements IShopService {

    @Autowired
    private ShopInfoMapper shopInfoMapper;


    @Autowired
    private JedisClusterUtils jedisClusterUtils;

    @Autowired
    private RedisParam redisParam;

    @Override
    public PageInfo<ShopInfo> queryAll(QueryShop query) {
        int page = query.getPage();
        int size = query.getSize();
        /*Page<ShopInfo> questionPage = */PageHelper.startPage(page, size);
        List<ShopInfo> list = shopInfoMapper.queryPage(query);
        //TODO 由于商店详情数据在启动时已预热在redis缓存中,是否还需在这儿添加
        for (ShopInfo info:list) {
            String key = redisParam.getGoodsShopBaseInfoKey().replaceAll("_", ":") + ":" + info.getId();
            jedisClusterUtils.set(key, info);
        }
      /*  return new PageInfo<>(questionPage);*/  //更改原因:pageHelper插件使用方式有问题
        PageInfo<ShopInfo> pageInfo = new PageInfo<>(list);
        return  pageInfo;
    }
}
