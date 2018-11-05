package com.bst.goods.controller;


import com.bst.common.entity.goods.GoodsSpecValueEntity;
import com.bst.common.modle.Result;
import com.bst.goods.service.GoodsSpecValueService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("goodSpecValue")
public class GoodsSpecValueController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsSpecController.class);

    @Autowired
    private GoodsSpecValueService goodsSpecValueService;


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取该规格所有规格值",notes = "需要传入规格ID")
    public Result info(@PathVariable("id") Long id){
        try {
            if (id == null || id < 0){
                return  Result.error("查询规格值失败: 入参错误");
            }
            List<GoodsSpecValueEntity> goodsSpecValueEntity = goodsSpecValueService.queryEntityValue(id);
            return Result.ok("获取成功", goodsSpecValueEntity);
        } catch (Exception e) {
            LOGGER.error(">>>>>>>>>>查询规格值，异常：",e);
            return Result.error("查询规格值异常");
        }
    }

    @PostMapping(value = "/save")
    @ApiOperation(value="保存自定义规格值",notes = "需要传入规格ID,对应的规格值和对应图片")
    public Result saveSpecValueList(@RequestBody List<Map<String,Object>> params){
         try {
              if (params == null ){
                  return Result.error("保存规格值失败: 入参错误");
              }
             goodsSpecValueService.saveSpecValueList(params);
              return  Result.ok("保存成功",params);
         } catch (Exception e){
             LOGGER.error(">>>>>>>>>保存规格值，异常：",e);
             return  Result.error("保存规格值异常");
         }
    }
}
