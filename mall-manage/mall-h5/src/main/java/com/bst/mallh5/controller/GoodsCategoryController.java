package com.bst.mallh5.controller;

import com.bst.common.entity.goods.GoodsCategory;
import com.bst.common.modle.Result;
import com.bst.common.modle.goods.GoodsSpuCategoryResponse;
import com.bst.mallh5.model.goods.GoodsCategoryResquest;
import com.bst.mallh5.service.goods.GoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zouqiang
 * @create 2018-11-01 14:33
 **/
@RestController
@RequestMapping("mallh5/category")
@Api(value = "mallH5_category", description = "商品分类的接口")
public class GoodsCategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsCategoryController.class);

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @GetMapping("queryCategory")
    public Result  queryGoodsCategory(@RequestParam("id") Long pid, @RequestParam("shopId") Long shopId) {
        Result result ;
        try {
            result = goodsCategoryService.queryGoodsCategory(pid, shopId);
            return result;
        } catch (Exception ex) {
             result = new Result();
            result.setStatus(500);
            result.setMsg("fail");
            return result;
        }
    }
    @ApiOperation(value = "queryAllCategory", notes = "递归商品分类的接口")
    @GetMapping("queryAllCategory")
    public Result  queryAllCategory(@RequestParam("shopId") Long shopId) {
        //默认商家一级分类为0
        Long pid = 0L;
        Result result ;
        try {
            result = goodsCategoryService.queryAllCategory(pid, shopId);
            return result;
        } catch (Exception ex) {
            result = new Result();
            result.setStatus(500);
            result.setMsg("fail");
            return result;
        }
    }

    @PostMapping("querySpuGoods")
   public Result querySpuByCategoryId(@RequestBody GoodsCategoryResquest goodsCategoryResquest){
        Result result ;
        try {
            Long categoryId = goodsCategoryResquest.getId();
            Long shopId = goodsCategoryResquest.getShopId();
            Integer page = goodsCategoryResquest.getPage();
            Integer rows = goodsCategoryResquest.getRows();
            result = goodsCategoryService.querySpuByCategoryId(categoryId, shopId , page,  rows);
            return result;
        }catch (Exception ex){
            result = new Result();
            result.setStatus(500);
            result.setMsg("fail");
            return result;
        }
   }

   @PostMapping("queryAllSpu")
    public Result queryAllSpuByShopId(@RequestBody GoodsCategoryResquest goodsCategoryResquest){
       Result result ;
       try {
           Long shopId = goodsCategoryResquest.getShopId();
           Integer page = goodsCategoryResquest.getPage();
           Integer rows = goodsCategoryResquest.getRows();
           result = goodsCategoryService.queryAllSpuByShopId(shopId , page,  rows);
           return result;
       }catch (Exception ex){
           result = new Result();
           result.setStatus(500);
           result.setMsg("fail");
           return result;
       }
   }
}
