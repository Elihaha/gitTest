package com.bst.mallh5.service.orders.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bst.common.config.Snowflake.IdGenerate;
import com.bst.common.config.Snowflake.SnowflakeId;
import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.entity.order.*;
import com.bst.common.entity.shop.ShippingAddress;
import com.bst.common.entity.shop.ShopInfo;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.common.mapper.goods.GoodsSpuMapper;
import com.bst.common.mapper.order.*;
import com.bst.common.mapper.shop.ShippingAddressMapper;
import com.bst.common.mapper.shop.ShopInfoMapper;
import com.bst.common.modle.goods.GoodsPicNameQuery;
import com.bst.common.modle.order.AllOrdersQuery;
import com.bst.common.modle.orders.DetailResult;
import com.bst.common.pojo.Pagination;
import com.bst.common.service.GoodsRedisService;
import com.bst.mallh5.model.orders.OrdersCreatePOJO;
import com.bst.mallh5.service.orders.OrderService;
import com.bst.mallh5.service.orders.model.ShippingAddressRequest;
import com.bst.mallh5.user.PlatformUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 订单业务层实现
 *
 * @author zouqiang
 * @create 2018-09-17 17:35
 **/
@Service
@Primary
@Slf4j
@Transactional(rollbackFor = Throwable.class, readOnly = false)
public class OrderServiceImpl implements OrderService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private GoodsRedisService goodsRedisService;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    @Autowired
    private GoodsSpuMapper goodsSpuMapper;
    @Autowired
    private ShopInfoMapper shopInfoMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderChildMapper orderChildMapper;
    @Autowired
    private OrderLogisticsDao orderLogisticsDao;
    @Autowired
    private ShippingAddressMapper shippingAddressMapper;
    @Autowired
    private PostageConfigDao postageConfigDao;

    /**
     * 在插入数据的之前包装
     *
     * @param count
     * @param nOrders
     * @param goodsSku
     * @param goodsSpu
     */
    public void beforeInsertOrder( OrdersCreatePOJO build) {
        Orders nOrders = build.getNOrders();
        //  获取商品相对应  商店名称
        ShopInfo shopInfo = shopInfoMapper.selectByPrimaryKey(build.getShopId());
        //  设置  orderid
        nOrders.setId(SnowflakeId.getId());//生成唯一的子订单编号
        //  设置  orderNo
        nOrders.setOrderNo(IdGenerate.generate(IdGenerate.ORDER_NO_PREFIX));//生成唯一的子订单编号

        //   获取当前登录 人员信息
       //PlatformUser user = (PlatformUser) SecurityUtils.getSubject().getPrincipal();
       // nOrders.setConsumerId(user.userUniqueFlag());
        //nOrders.setConsumerName(user.getNickName());
        nOrders.setConsumerId("zou123");
        nOrders.setConsumerName("zouqiang");
        nOrders.setTelephone(build.getSignerPhone());

        nOrders.setShopId(shopInfo.getId());
        nOrders.setShopName(shopInfo.getShopName());
        nOrders.setCreateTime(new Date());

        //-1.删除、0.草稿、1.待支付、2.已支付，3取消支付、4.取消订单、5.待发货、6发货中、7已收货、8退货中、9.已退货、10.已退款。',
        nOrders.setOrderStatus((byte) 1);
        nOrders.setPayStatus((byte) 1);

        nOrders.setGoodsCount(build.getCount());

        //注意判斷空？？？？？？？？？
        BigDecimal currentPostageMerchant = goodsRedisService.getCurrentPostageMerchant(build.getProvince(), shopInfo.getId());
        nOrders.setPostage(currentPostageMerchant);
        BigDecimal decimal = new BigDecimal(build.getCount());
        //nOrders.setTotalAmount(goodsSku.getSellPrice().multiply(Count));
        BigDecimal totalAmount = build.getSellPrice().multiply(decimal).add(currentPostageMerchant);
        nOrders.setTotalAmount(totalAmount);
        nOrders.setConsumerRemark("");
        //支付时间
        nOrders.setPayTime(null);
        nOrders.setIsReturn(false);
        nOrders.setSalesReturnTime(null);
        nOrders.setHaveChild(false);


    }

    //创建订单详细
    public int createOrderDetail(Long orderId, String orderNo, String spuNo, String skuNo, String orderDetails) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setOrderNo(orderNo);
        orderDetail.setSpuNo(spuNo);
        orderDetail.setSkuNo(skuNo);
        orderDetail.setOrderDetails(orderDetails);
        int num = orderDetailMapper.insert(orderDetail);
        return num;
    }

    //查询订单详情

    public List queryOrderDetail(Long orderid) {
        OrderDetailExample example = new OrderDetailExample();
        OrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderid);
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByExample(example);
        OrderDetail orderDetail = orderDetailList.get(0);
        String theorderDetails = orderDetail.getOrderDetails();
        JSONObject orderDetails = JSON.parseObject(theorderDetails);
        DetailResult detailResult = ordersMapper.queryOrderDetail(orderid);
        if (detailResult == null) {
            detailResult = new DetailResult();
        }
        ShippingAddress shippingAddress = shippingAddressMapper.queryByOrderId(orderid);
        List<Object> list = new ArrayList<>();
        list.add(orderDetails);
        list.add(detailResult);
        list.add(shippingAddress);
        return list;


    }

    @Override
    public OrderDetail getOrderDetailByOrderId(Long orderId) {
        return orderDetailMapper.selectOrderDetailByOrderId(orderId);
    }

    private HashMap<String, Object> getStringObjectHashMap(Integer page, Integer rows) {
        return new HashMap<String, Object>() {{
            int value = (page - 1) * rows;
            put("page", value<0?0:value);
            put("rows", rows);
        }};
    }


    //查询所有主订单
    public Map queryOrdersList(Integer page, Integer rows, Byte orderStatus) {
        //PlatformUser user = (PlatformUser) SecurityUtils.getSubject().getPrincipal();
       // String userId = user.userUniqueFlag();
        String userId = "zou123";
        Map<String, Object> requestmap = getStringObjectHashMap(page, rows);
        requestmap.put("userId", userId);
        requestmap.put("ordersStatus", orderStatus);
        List<AllOrdersQuery> allOrdersQueryList = ordersMapper.queryAllOrderDetail(requestmap);
        Map<String, Object> map = new HashMap<>();
        if (!allOrdersQueryList.isEmpty())
            for (AllOrdersQuery allOrdersQuery : allOrdersQueryList) {
                String theorderDetails = allOrdersQuery.getOrderDetails();
                GoodsPicNameQuery orderDetails = JSON.parseObject(theorderDetails, GoodsPicNameQuery.class);
                allOrdersQuery.setOrderDetailsList(orderDetails);
            }
        int total = ordersMapper.queryCount(requestmap);

        map.put("list", allOrdersQueryList);
        map.put("total", total);

        return map;

    }

    //插入主订单
    public int insertOrders(Orders orders) {
        return ordersMapper.insertSelective(orders);
    }

    //通过订单号查询订单
    public Orders queryOrdersByOrdersNo(String OrderNo) {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(OrderNo);
        Orders orders = ordersMapper.selectByOrderNo(example);
        return orders;
    }

    //更行更新spu的库存
    public boolean updateSpuStock(String spuNo, int count) {
        return goodsRedisService.delCountBySpuNO(spuNo, count);
    }

    public boolean updateSkuStock(String skuNo, int count) {
        return goodsRedisService.delCountBySkuNO(skuNo, count);
    }

    //更新支付时间，和支付方式,订单状态，支付状态
    public int updatePayType(long orderid, Date paytime, int paytype, byte orderstatus, byte paystatus) {
        Orders orders = ordersMapper.selectByPrimaryKey(orderid);
        orders.setPayTime(paytime);
        orders.setOrderStatus(orderstatus);
        orders.setPayStatus(paystatus);
        int orderNum = ordersMapper.insert(orders);
        OrderDetailExample example = new OrderDetailExample();
        OrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderid);
        List<OrderDetail> list = orderDetailMapper.selectByExample(example);
        OrderDetail orderDetail = list.get(0);
        int DetailNum = orderDetailMapper.insert(orderDetail);

        if (orderNum == 0 || DetailNum == 0) {
            return 0;
        }
        return 1;
    }

    //更新订单状态
    public int updateOrderStatus(long orderid, Byte orderstatus) {
        Orders orders = ordersMapper.selectByPrimaryKey(orderid);
        orders.setOrderStatus(orderstatus);
        return ordersMapper.insert(orders);

    }

    //更新收货状态
    public int updateOrderStatus(long orderid) {
        return ordersMapper.updateOrdersStatus(orderid);

    }

    @Override
    public void updateByPrimaryKeySelective(Orders order) {
        ordersMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public long updateOrderFinishPay(String orderNo, byte payType, byte fromStatus) {
        return ordersMapper.updateOrderPayStatus(orderNo, payType, fromStatus);
    }

    @Override
    public Map queryOrderDetailStatus() {
        PlatformUser user = (PlatformUser) SecurityUtils.getSubject().getPrincipal();
        return ordersMapper.queryOrderDetailStatus(user.getAuthCacheKey());
    }


}
