package com.bst.mallh5.controller;

import com.bst.common.modle.Result;
import com.bst.common.modle.goods.GoodsRedisInfo;
import com.bst.common.modle.goods.SkuInfoQuery;
import com.bst.mallh5.model.goods.GoodsRequest;
import com.bst.mallh5.service.goods.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author lumin
 * @description: 商品相关的接口
 * @create 2018-09-17 17:28
 **/
@RestController
@RequestMapping("mallh5/goods/")
@Api(value = "mallH5_goods", description = "商品相关的接口<author:lumin>")
public class GoodsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询商品详情
     *
     * @param request
     * @return
     */
    @PostMapping("findGoodsDetailById")
    @ApiOperation(value = "查询商品详情", notes = "根据传入的id,查询指定商品详情")
    public Result findGoodsDetailById(@RequestBody GoodsRequest request) {
        try {
            if (request == null || request.getGoodsId() < 1) {
                return Result.error("查询失败：入参错误");
            }
            return goodsService.findGoodsDetailById(request);
        } catch (Exception e) {
            LOGGER.error(">>>>>>>>>>>>>>查询商品详情失败，异常：", e);
            return Result.error("查询失败：系统异常");
        }
    }

    @GetMapping("findGoodsSkuBySpuId/{spuId}")
    @ApiOperation(value = "查询商品规格集合信息", notes = "根据传入的商品id,查询指定商品的所有规格集合")
    public Result findGoodsSkuBySpuId(@PathVariable("spuId") Long spuId) {
        try {
            if (spuId == null || spuId < 1) {
                return Result.error("查询失败：入参错误");
            }
            return goodsService.queryGoodsSkuList(spuId);
        } catch (Exception e) {
            LOGGER.error(">>>>>>>>>>>>>>查询商品详情失败，异常：", e);
            return Result.error("查询失败：系统异常");
        }
    }

    @ApiOperation(value = "获取spu 所有的sku 信息 ", response = Result.class)
    @GetMapping("getAllSkuInfo/{spuNo}")
    @ApiImplicitParam(value = "获取spu ", paramType = "path", dataTypeClass = String.class, defaultValue = "1", name = "spuNo")
    public Result getAllSkuInfo(@PathVariable("spuNo") String spuNo) {
        try {
            if (spuNo == null) {
                return Result.error("商品操作失败：入参错误");
            }
            Set<SkuInfoQuery> allSkuInfoBySpuNo = goodsService.getAllSpecSkuInfoBySpuNo(spuNo);
            return Result.ok(allSkuInfoBySpuNo);
        } catch (Exception e) {
            LOGGER.error(">>>>>>>>>>查询商品列表，异常：", e);
            return Result.error("查询商品列表失败：系统异常");
        }
    }

    @ApiOperation(value = "  所有商品详情首页  ", response = Result.class)
    @GetMapping("getAllSpuInfoByShopId/{shopId}/{page}/{number}")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "获取shopId 所有的sku 商品详情首页 ", paramType = "path", dataTypeClass = String.class, defaultValue = "1", name = "shopId"),
            @ApiImplicitParam(value = "页数  从1 开始 ", paramType = "path", dataTypeClass = String.class, defaultValue = "1", name = "page"),
            @ApiImplicitParam(value = "每页多少条", paramType = "path", dataTypeClass = String.class, defaultValue = "1", name = "number")
    })
    public Result getAllSpuInfoByShopId(@PathVariable("shopId") Long shopId, @PathVariable("page") Integer page, @PathVariable("number") Integer number) {
        try {
            if (shopId == null) {
                return Result.error("商品操作失败：入参错误");
            }
            int value = (page - 1) * number;
            Integer offset = value < 0 ? 0 : value;
            Integer limit = (page * number)-1;
            return goodsService.getAllSpuInfoByShopId(shopId, offset, limit);
        } catch (Exception e) {
            LOGGER.error(">>>>>>>>>>查询商品列表，异常：", e);
            return Result.error("查询商品列表失败：系统异常");
        }
    }


}
