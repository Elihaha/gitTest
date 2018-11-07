package com.bst.mallh5.controller;

import com.bst.common.modle.Result;
import com.bst.mallh5.service.orders.OrderRefundService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zouqiang
 * @create 2018-11-05 11:45
 **/
@RestController
@RequestMapping("mallh5/refund")
public class OrderRefundController {

    @Autowired
    private OrderRefundService orderRefundService;
    @ApiOperation(value = "取消退款",notes = "orderNo 订单的编号")
    @PutMapping("cancelRefund")
    public Result cancelRefund(@RequestParam(value="orderNo") String orderNo){
        Result result = new Result();
        try {
            orderRefundService.cancelRefund(orderNo);
            result.setStatus(200);
            result.setMsg("success");
            return result;
        }catch (Exception ex){
            result.setStatus(500);
            result.setMsg("fail");
            return result;
        }
    }
}
