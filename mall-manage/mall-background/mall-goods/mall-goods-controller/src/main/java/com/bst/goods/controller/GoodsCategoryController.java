package com.bst.goods.controller;

import com.bst.common.entity.goods.GoodsCategory;
import com.bst.common.modle.Result;
import com.bst.goods.service.GoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "查询分类", notes = "成功返回结果200 success 失败返回 500 ")
    @GetMapping("/query/{id}")
    public Result queryGoodsCategory(@PathVariable("id") Long pid){
       Result result;
            result = goodsCategoryService.queryGoodsCategory(pid);
            return result;
    }

    @ApiOperation(value = "递归查询分类", notes = "成功返回结果200 success 失败返回 500 ")
    @GetMapping("/queryall")
    public Result queryAllCategory(){
        //默认商家一级分类为0
        Long pid = 0L;
        Result result;
        result = goodsCategoryService.queryAllCategory(pid);
        return result;
    }

    @ApiOperation(value = "分类添加", notes = "成功返回结果200 success 失败返回 500 ")
    @PostMapping("/insert")
    public Result addGoodsCategoryByPid(@RequestParam("id") Long pid,@RequestParam("name") String name){
        Result result = new Result();
        try {
            goodsCategoryService.addGoodsCategoryByPid(pid,name);
            result.setMsg("success");
            result.setStatus(200);
            return result;
        }catch (Exception ex){
            result.setMsg("fail");
            result.setStatus(500);
            return result;
        }
    }

    @ApiOperation(value = "分类更新", notes = "成功返回结果200 success 失败返回 500 ")
    @PutMapping("/update")
    public Result updateCategoryByPid(@RequestParam("id") Long id,@RequestParam("name") String name){
        Result result = new Result();
        try {
            goodsCategoryService.updateCategoryByPid(id,name);
            result.setMsg("success");
            result.setStatus(200);
            return result;
        }catch (Exception ex){
            result.setMsg("fail");
            result.setStatus(500);
            return result;
        }
    }

    @ApiOperation(value = "分类删除", notes = "成功返回结果200 success 失败返回 500 ")
    @DeleteMapping("/delete/{id}")
    public Result deleteCategoryByPid(@PathVariable("id") Long pid){

        Result result =  goodsCategoryService.deleteCategoryByPid(pid);
            return result;
        }

}
