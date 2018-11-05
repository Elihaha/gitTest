package com.bst.order.controller;

import com.bst.common.entity.order.OrderChild;
import com.bst.common.modle.Result;
import com.bst.order.model.OrdersPageQuery;
import com.bst.order.service.OrdersManageService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 订单管理
 *
 * @author zouqiang
 * @create 2018-09-19 10:30
 **/
@RestController
@RequestMapping("ordermanager")
public class OrdersManageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersManageController.class);
    //查询订单
    @Autowired
    private OrdersManageService ordersService;



    //查询所有子订单
    @GetMapping("queryOrderChildsList/{ordersId}")
    @ApiOperation(value = "查询所有子订单",notes = "OrdersId 根据主订单id ")
    public Result queryOrderChildsList(@PathVariable("ordersId") Long ordersId){
        Result result = new Result();
        try {
            List<OrderChild> list =  ordersService.queryOrderChildsList(ordersId);
            result.setData(list);
            result.setMsg("查询成功");
            result.setStatus(200);
            return result;
        }catch (Exception ex){
            result.setMsg("查询失败");
            result.setStatus(500);
            return result;
        }
    }

    //查询子订单
    @GetMapping("/queryOrderChild/{orderChildId}")
    @ApiOperation(value = "查询子订单",notes = "orderChildId 根据子订单id ")
    public Result queryOrderChild(@PathVariable("orderChildId") Long orderChildId){
        Result result = new Result();
        try {
            OrderChild orderChild = ordersService.queryOrderChild(orderChildId);
            result.setData(orderChild);
            result.setMsg("查询成功");
            result.setStatus(200);
            return result;
        }catch (Exception ex){
            result.setMsg("查询失败");
            result.setStatus(500);
            return result;
        }
    }

    //修改订单状态
    @GetMapping("/updateOrderStatus")
    @ApiOperation(value = "修改订单状态",notes = "Long ordersId 订单ID,int orderStatus 订单的状态")
    public Result updateOrderStatus(Long ordersId ,int orderStatus){
        Result result = new Result();
        try {
           int count =  ordersService.updateOrderStatus(ordersId ,orderStatus);
            result.setData(count);
            result.setMsg("查询成功");
            result.setStatus(200);
            return result;
        }catch (Exception ex){
            LOGGER.error(">>>>>>>>>>修改订单状态，异常：",ex);
            result.setMsg("查询失败");
            result.setStatus(500);
            return result;
        }
    }


    @PostMapping("queryOrdersList")
    @ResponseBody
    @ApiOperation(value = "查询订单列表",notes = "根据订单状态和订单名称模糊查询")
    public Result queryGoodsList(@RequestBody OrdersPageQuery query){
        try {
            if(query==null || query.getPageNumKey()<0 || query.getPageSizeKey()<1){
                return Result.error("查询订单列表出错：缺少分页参数");
            }
            Result result = ordersService.queryOrdersList(query);
            return result;
        }catch (Exception e){
            LOGGER.error(">>>>>>>>>>查询订单列表，异常：",e);
            return Result.error("查询订单列表失败：系统异常");
        }
    }

    @PostMapping("queryOrdersListBy")
    @ResponseBody
    @ApiOperation(value = "条件查询订单列表",notes = "根据订单状态和订单名称模糊查询")
    public Result queryGoodsListBy(@RequestBody OrdersPageQuery query){
        try {
            if(query==null || query.getPageNumKey()<0 || query.getPageSizeKey()<1){
                return Result.error("查询订单列表出错：缺少分页参数");
            }
            Result result = ordersService.queryOrdersListBy(query);
            return result;
        }catch (Exception e){
            LOGGER.error(">>>>>>>>>>查询订单列表，异常：",e);
            return Result.error("查询订单列表失败：系统异常");
        }
    }

    ////创建子订单
    @PostMapping("createOrderChild")
    @ResponseBody
    @ApiOperation(value = "条件查询订单列表",notes = "根据订单状态和订单名称模糊查询")
    public Result createOrderChild(Long orderId, String explain, int goodsCount, String remark){
        Result result = new Result();
        try {
        ordersService.createOrderChild(orderId,explain,goodsCount,remark);
            result.setMsg("插入成功");
            result.setStatus(200);
            return result;
        }
    catch (Exception e){
        LOGGER.error(">>>>>>>>>>插入，异常：",e);
        result.setStatus(200);
         result.setMsg("插入失败：系统异常");
        return result;
    }
    }


}
