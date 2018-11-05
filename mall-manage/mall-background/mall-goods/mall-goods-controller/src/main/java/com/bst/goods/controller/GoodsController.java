package com.bst.goods.controller;

import com.alibaba.fastjson.JSON;
import com.bst.common.constants.CommonConstants;
import com.bst.common.modle.Result;
import com.bst.common.modle.goods.SkuInfoQuery;
import com.bst.common.service.GoodsRedisService;
import com.bst.goods.model.GoodsPageQuery;
import com.bst.goods.model.GoodsSpuRequest;
import com.bst.goods.service.GoodsImageService;
import com.bst.goods.service.GoodsSpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author lumin
 * @description: 商品相关的接口
 * @create 2018-09-17 17:28
 **/
@RestController
@RequestMapping("mallGoods/goods")
@Api(value = "mallgoods_goods",description = "这是商品后台管理相关接口的描述")
public class GoodsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);



    @Autowired
    private GoodsSpuService goodsSpuService;




    /**
     * 保存商品,并将商品信息存入redis
     * @param request
     * @return
     */
    @PostMapping("saveGoods")
    @ResponseBody
    @ApiOperation(value="添加商品")
    public Result saveGoods(@RequestBody GoodsSpuRequest request){
        try {
            if(request==null || StringUtils.isEmpty(request.getGoodsName())
                    || StringUtils.isEmpty(request.getGoodsDetail()) || StringUtils.isEmpty(request.getGoodsPrice())){
                return Result.error("添加商品失败：入参不完整");
            }
            if(request.getCategoryId()<1){
                return Result.error("添加商品失败：传入的商品分类不正确");
            }
            if(request.getTotalStock()<1){
                return Result.error("添加商品失败：传入的商品库存少于1");
            }
            if(request.getImagesList()==null || request.getImagesList().size()<1){
                return Result.error("添加商品失败：缺少商品的展示图片");
            }
            Result result = goodsSpuService.saveGoods(request);
            return result;
        }catch (Exception e){
            LOGGER.error(">>>>>>>>>>添加商品，异常：",e);
            return Result.error("创建商品失败：系统异常");
        }
    }

    @PostMapping("updateGoodsInfo")
    @ResponseBody
    @ApiOperation(value = "编辑商品信息",notes = "更新商品时，必须要传入商品Id")
    public Result updateGoodsInfo(@RequestBody GoodsSpuRequest request){
        try {
            LOGGER.info(">>>>>编辑商品接口，入参：【{}】", JSON.toJSONString(request));
            if(request==null || request.getGoodsId()==null || request.getGoodsId()<1){
                return  Result.error("查询失败：入参错误");
            }
            Result result = goodsSpuService.updateGoods(request);
            return result;
        }catch (Exception e){
            LOGGER.error(">>>>>>>>>>编辑商品，异常：",e);
            return Result.error("编辑商品失败：系统异常");
        }
    }

    @PostMapping("updateGoodsInfoAndOnShelf")
    @ResponseBody
    @ApiOperation(value = "编辑商品信息并上架出售", notes = "更新商品并上架时,必须传入商品Id")
    public Result updateGoodsInfoAndOnShelf(@RequestBody GoodsSpuRequest request){
        try {
            LOGGER.info(">>>>>编辑商品接口并上架出售，入参：【{}】", JSON.toJSONString(request));
            if (request == null || request.getGoodsId() == null || request.getGoodsId() < 1) {
                return Result.error("查询失败：入参错误");
            }
            goodsSpuService.updateGoods(request);
            request.setGoodsStatus((byte) CommonConstants.goodsOnShelfStatus);
            Result result  = goodsSpuService.updateGoodsStatus(request);
            return result;
        }catch (Exception e){
            LOGGER.error(">>>>>>>>>>编辑商品接口并上架出售，异常：",e);
            return Result.error("编辑商品接口并上架出售失败：系统异常");
        }
    }



    @GetMapping("queryGoodsById/{goodsId}")
    @ResponseBody
    @ApiOperation(value = "查询商品详情",notes = "根据传入的goodsId,查询商品详情")
    public Result queryGoodsById(@PathVariable("goodsId") Long goodsId){
        try {
            if(goodsId==null || goodsId<1){
                return  Result.error("查询失败：入参错误");
            }
            Result result = goodsSpuService.queryGoodsById(goodsId);
            return result;
        }catch (Exception e){
            LOGGER.error(">>>>>>>>>>查询商品，异常：",e);
            return Result.error("查询商品失败：系统异常");
        }
    }

    @PostMapping("queryGoodsList")
    @ResponseBody
    @ApiOperation(value = "查询商品列表",notes = "根据商品状态和商品名称模糊查询")
    public Result queryGoodsList(@RequestBody GoodsPageQuery query){
        try {
            if(query==null || query.getPageNumKey()<0 || query.getPageSizeKey()<1){
                return Result.error("查询商品列表出错：缺少分页参数");
            }
            Result result = goodsSpuService.queryGoodsList(query);
            return result;
        }catch (Exception e){
            LOGGER.error(">>>>>>>>>>查询商品列表，异常：",e);
            return Result.error("查询商品列表失败：系统异常");
        }
    }

    /**
     * 上架/下架/删除商品
     * 上架存入redis
     * 下架从redis中清除
     * @param request
     * @return
     */
    @PostMapping("updateGoodsStatus")
    @ResponseBody
    @ApiOperation(value = "商品的操作",notes = "根据传入的goodsId和goodsStatus(1上架中，2已下架，3删除)修改商品的状态")
    public Result updateGoodsStatus(@RequestBody GoodsSpuRequest request){
        try {
            if(request==null || request.getGoodsId()<0 || request.getGoodsStatus() < 1 || request.getGoodsStatus()>3){
                return Result.error("商品操作失败：入参错误");
            }
            Result result = goodsSpuService.updateGoodsStatus(request);

            return result;
        }catch (Exception e){
            LOGGER.error(">>>>>>>>>>查询商品列表，异常：",e);
            return Result.error("查询商品列表失败：系统异常");
        }
    }

    @PostMapping("batchUpdateGoodsStatus")
    @ResponseBody
    @ApiOperation(value = "商品的批量操作",notes = "根据传入的ids集合和goodsStatus(1上架中，2已下架，3删除)批量修改商品的状态")
    public Result batchUpdateGoodsStatus(@RequestBody GoodsSpuRequest request){
        try {
            if(request==null || request.getIds()==null || request.getIds().size()<1 || request.getGoodsStatus() < 1 || request.getGoodsStatus()>3){
                return Result.error("商品操作失败：入参错误");
            }
            Result result = goodsSpuService.batchUpdateGoodsStatus(request);
            return result;
        }catch (Exception e){
            LOGGER.error(">>>>>>>>>>查询商品列表，异常：",e);
            return Result.error("查询商品列表失败：系统异常");
        }
    }


    @ApiOperation(value = "获取spu 所有的sku 信息 ", response = Result.class)
    @GetMapping("getAllSkuInfo/{spuNo}")
    @ApiImplicitParam(value = "获取spu 所有的sku 信息", paramType = "path", dataTypeClass = String.class, defaultValue = "1", name = "spuNo")
    public Result getAllSkuInfo(@PathVariable("spuNo") String spuNo) {
        try {
            if (spuNo == null) {
                return Result.error("商品操作失败：入参错误");
            }
            Set<SkuInfoQuery> allSkuInfoBySpuNo = goodsSpuService.getAllSpecSkuInfoBySpuNo(spuNo);
            return Result.ok(allSkuInfoBySpuNo);
        } catch (Exception e) {
            LOGGER.error(">>>>>>>>>>查询商品列表，异常：", e);
            return Result.error("查询商品列表失败：系统异常");
        }
    }


}
