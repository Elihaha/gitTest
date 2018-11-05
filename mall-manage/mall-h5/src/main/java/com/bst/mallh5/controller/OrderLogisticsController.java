package com.bst.mallh5.controller;


import com.bst.common.constants.HttpConstants;

import com.bst.common.entity.order.OrderLogisticsEntity;
import com.bst.common.modle.Result;
import com.bst.common.pojo.QueryKuaidiJson;
import com.bst.common.utils.KuaiDI100Util;

import com.bst.mallh5.service.order.OrderLogisticsHService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * 订单物流表
 *
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-17 19:27:22
 */
@RestController
@Slf4j
@Api("订单物流查询")
@RequestMapping("/orderlogistics")
public class OrderLogisticsController {
    @Autowired
    private OrderLogisticsHService orderLogisticsService;


    @ApiOperation("单个订单物流查询 ")
    @ApiImplicitParam( name = "orderNo" ,value = "orderNo", required = false, dataType = "string", paramType = "path", defaultValue = "3")
    @GetMapping("/queryInfo/{orderNo}")
    public Result queryLogisticsInfo(@PathVariable(value = "orderNo") String orderNo) {
        try {
            if(StringUtils.isBlank(orderNo)){
                return Result.error(" 参数需要大于0");
            }
            return orderLogisticsService.queryObject(orderNo);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Result.error(HttpConstants.UNKNOW+"");
        }
    }


}
