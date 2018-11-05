package com.bst.goods.controller;

import com.bst.common.entity.goods.GoodsCategory;
import com.bst.common.modle.Result;
import com.bst.goods.model.CategoryPageQuery;
import com.bst.goods.service.GoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zouqiang
 * @create 2018-09-19 12:24
 **/
@Api(value = "GoodsCategory",description = "这是商品分类接口详细信息的描述")
@RestController
@RequestMapping("GoodsCategory")
public class GoodsCategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsCategoryController.class);

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @ApiOperation(value = "查询商品分类", notes = "成功返回结果200 success 失败返回 500 ")
    @PostMapping("/query/{id}")
    public Result queryGoodsCategory(@PathVariable("id") Long id){
        Result result = new Result();
        try {
            GoodsCategory goodsCategory = goodsCategoryService.queryGoodsCategory(id);
            result.setData(goodsCategory);
            result.setMsg("查询成功");
            result.setStatus(200);
            return result;
        }catch (Exception ex){
            result.setMsg("查询失败");
            result.setStatus(500);
            return result;
        }
    }
    //查询所有商品分类
    @ApiOperation(value = "查询所有商品类型分类", notes = "成功返回结果200 success 失败返回 500 ")
    @GetMapping("/queryList")
    public Result queryGoodsCategoryList(@ApiParam CategoryPageQuery categoryPageQuery) {
        try {
            Result result =  goodsCategoryService.queryGoodsCategoryList(categoryPageQuery);
            return result;
        } catch (Exception ex) {
            LOGGER.error(">>>>>>>>>>查询列表，异常：",ex);
            return Result.error("查询订单列表失败：系统异常");
        }
    }

}
