package com.bst.user.permission.shop.controller;

import com.bst.common.entity.shop.QueryShop;
import com.bst.common.entity.shop.ShopInfo;
import com.bst.common.modle.Result;
import com.bst.user.permission.shop.service.IShopService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商店管理控制层
 */
@RestController
@RequestMapping("/shop/manager")
public class ShopManagerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IShopService shopService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Result> queryShop(QueryShop query) {
        PageInfo<ShopInfo> pageBean;
        try {
            pageBean = shopService.queryAll(query);
        } catch (Exception ex) {
            logger.error("查询商店列表出错！", ex);
            Result result = Result.error(ex.getMessage());
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(Result.ok(pageBean));
    }
}
