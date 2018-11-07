package com.bst.mallh5.controller;
import com.bst.common.entity.order.OrderChild;
import com.bst.common.entity.shop.ShippingAddress;
import com.bst.common.modle.Result;
import com.bst.common.modle.orders.DetailResult;
import com.bst.mallh5.service.orders.OrderService;
import com.bst.mallh5.service.orders.impl.OrderCreateService;
import com.bst.mallh5.service.orders.model.OrdersRequest;
import com.bst.mallh5.service.orders.model.ShippingAddressRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 订单交互接口
 *
 * @author zouqiang
 * @create 2018-09-17 17:42
 **/

@Slf4j
@RestController
@RequestMapping("mallh5/orders")
public class OrderController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderCreateService createOrder;   @Autowired
    private OrderService orderService;
    //创建订单
    @ApiOperation(value = "创建主订单",notes = "long goodsSkuId sku表的ID, int count 商品数量,String  ConsumerRemark客户备注,ShippingAddressRequest shippingAddressRequest收货地址")
    @PostMapping("/createOrder")
    public Result createOrder(@RequestBody OrdersRequest request){
        int  count= request.getCount();
        String goodsSkuNo =request.getGoodsSkuNo();
        ShippingAddressRequest shippingAddressRequest = request.getShippingAddressRequest();
        Result result;
        try {
            result =  createOrder.createOrder(goodsSkuNo, count,  shippingAddressRequest);
            return result;
        }catch(Exception ex){
          log.error(ex.getMessage(),ex);
            result = new Result();
            result.setMsg("创建失败");
            result.setStatus(500);
            return result;
        }
        //return userId;
    }


    //查询所有主订单
    @ApiOperation(value = "查询所有主订单",notes = "Integer page 页码, Integer rows 每页数量")
    @GetMapping("/queryOrderList")
    public Result queryOrdersList(Integer page, Integer rows ,Byte orderStatus) {
        Result result = new Result();
        try {
            Map map = orderService.queryOrdersList( page,  rows,orderStatus);
            if(map.equals(null)){
                result.setMsg("查询失败");
                result.setStatus(500);
            }
            result.setData(map);
            result.setMsg("查询成功");
            result.setStatus(200);
            return result;
        }catch(Exception ex){
            log.error("query main order error.", ex);
            result.setMsg("查询失败");
            result.setStatus(500);
            return result;
        }
    }


    //查看订单详情
    //查询订单详情
    @ApiOperation(value = "查询订单详情",notes = " Long orderid 主订单ID")
    @GetMapping("/queryOrderDetail")
    public Result queryOrderDetail(Long orderid){
        Result result = new Result();
        try{
            List list = orderService.queryOrderDetail(orderid);
            if(list.equals(null)){
                result.setMsg("没有查到数据");
                result.setStatus(500);
                return result;
            }

            result.setData(list);
            result.setMsg("查询成功");
            result.setStatus(200);
            return result;
        }catch(Exception ex){
            LOGGER.error(">>>>>>查询，异常：", ex);
            result.setMsg("查询失败");
            result.setStatus(500);
            return result;
        }
    }

    @PutMapping("/received/{orderId}")
    @ApiOperation(value = "确认收货",notes = " Long orderid 主订单ID ，Byte orderstatus 订单状态")
    public Result updateOrderDetail(@PathVariable("orderId") long orderId ){
        Result result = new Result();
        try {
           int sax =  orderService.updateOrderStatus(orderId);
           if(sax ==0 ){
               result.setMsg("修改已收货状态失败");
               result.setStatus(200);
               return result;
           }
          result.setMsg("修改已收货状态成功");
          result.setStatus(200);
          return result;
        }catch (Exception ex){
            log.error("Failed to modify received state",ex);
            result.setMsg("修改已收货状态失败");
            result.setStatus(200);
            return result;
        }
    }

     //查询订单详情状态
    @ApiOperation(value = "查询订单详情状态",notes = "订单详情状态")
    @GetMapping("/queryOrderDetailStatus")
    public Result queryOrderDetailStatus(){
        Result result  = new Result();
        try {
         Map OrderdetailStatus = orderService.queryOrderDetailStatus();
         result.setData(OrderdetailStatus);
         return result;
        }catch (Exception e){
            LOGGER.error("failed to query order details status",e);
            result.setMsg("查询订单详情状态失敗");
            result.setStatus(200);
            return result;
        }
    }

}
