package com.bst.mallh5.controller;

import com.bst.common.modle.Result;
import com.bst.common.service.GoodsRedisService;
import com.bst.mallh5.service.order.PostageConfigHService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-18 16:10:33
 */
@RestController
@Slf4j
@Api("运费配置")
@RequestMapping("postageconfig")
public class PostageConfigController {
    @Autowired
    private PostageConfigHService postageConfigService;

    @Autowired
    private GoodsRedisService goodsRedisService;

    /**
     * 删除
     */
    @ApiOperation(value = " 获取 运费    ", response = Result.class,notes = "name ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户收货地址，省名", defaultValue = "", dataTypeClass = String.class,paramType="path" ),
            @ApiImplicitParam(name = "goodsId", value = " 商品id", defaultValue = "", dataTypeClass = Long.class,paramType="path" )
    })
    @GetMapping("/getPostage/{goodsId}/{name}")
    public Result queryByProvince(@PathVariable(value = "name") String name,@PathVariable(value = "goodsId")  Long goodsId) {
        try {
           if(StringUtils.isBlank(name)){
                return  Result.error("省名字不能为null");
           }
           if(goodsId==null || goodsId<0){
                return  Result.error("goodsId 错误");
           }
            Result result = postageConfigService.queryByProvince(name, goodsId);
//            BigDecimal currentPostageMerchant = goodsRedisService.getCurrentPostageMerchant(name, goodsId);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

}
