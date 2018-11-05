package com.bst.user.permission.shop.service;

import com.bst.common.entity.shop.ShopInfo;
import com.bst.common.entity.shop.QueryShop;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 商店接口
 */
public interface IShopService {

    PageInfo<ShopInfo> queryAll(QueryShop query);
}
