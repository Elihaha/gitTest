package com.bst.order.service.impl;


import com.bst.backcommon.permission.PermissionInfoUtil;
import com.bst.backcommon.permission.entity.Operator;
import com.bst.common.config.Snowflake.IdGenerate;
import com.bst.common.config.Snowflake.SnowflakeId;

import com.bst.common.constants.OrdersConstants;
import com.bst.common.constants.ReturnOfGoodsConstants;
import com.bst.common.entity.order.*;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.common.mapper.goods.GoodsSpuMapper;
import com.bst.common.mapper.order.OrderChildMapper;
import com.bst.common.mapper.order.OrdersMapper;
import com.bst.common.modle.Result;

import com.bst.common.modle.order.OrderChildDto;
import com.bst.common.modle.order.OrderChildGenerate;
import com.bst.common.modle.order.OrderLogisticsInsertPojo;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import com.bst.order.model.OrdersPageQuery;
import com.bst.common.modle.orders.OrdersResult;
import com.bst.order.service.OrderDetailService;
import com.bst.order.service.OrderLogisticsService;
import com.bst.order.service.OrdersManageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;


/**
 * @author zouqiang
 * @create 2018-09-19 10:04
 **/
@Service
@Slf4j
@Transactional(value = "mallTransactionManager" ,readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class OrdersManageServiceImpl implements OrdersManageService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrdersManageServiceImpl.class);
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderChildMapper orderChildMapper;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private GoodsSpuMapper goodsSpuMapper;
    @Autowired
    private OrderLogisticsService orderLogisticsDao;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    @Autowired
    RedisParam redisParam;

    @Autowired
    private JedisClusterUtils jedisCluster;

    //主订单
    public Orders queryOrders(Long OrdersId) {
        return ordersMapper.selectByPrimaryKey(OrdersId);
    }

    @Override
    public Orders queryOrdersByOrderNo(String OrdersNo) {
        OrdersExample ordersExample = new OrdersExample();
        OrdersExample.Criteria criteria = ordersExample.createCriteria().andOrderNoEqualTo(OrdersNo);
        ordersExample.or(criteria);
        return ordersMapper.selectByOrderNo(ordersExample);
    }

    //查询所有主订单
    public List<Orders> queryOrdersList(Integer page, Integer rows) {
        OrdersExample example = new OrdersExample();
        //设置分页
        PageHelper.startPage(page, rows);
        List<Orders> list = ordersMapper.selectByExample(example);
        //取分页
        PageInfo<Orders> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        return list;

    }

    //查询所有子订单
    public List<OrderChild> queryOrderChildsList(Long OrdersId) {
        OrderChildExample example = new OrderChildExample();
        OrderChildExample.Criteria criteria = example.createCriteria();
        criteria.andMainIdEqualTo(OrdersId);
        return orderChildMapper.selectByExample(example);
    }

    //查询子订单
    public OrderChild queryOrderChild(Long OrderChildId) {
        return orderChildMapper.selectByPrimaryKey(OrderChildId);
    }

    //修改订单状态
    public int updateOrderStatus(Long OrdersId, int orderStatus) {
        Orders orders = ordersMapper.selectByPrimaryKey(OrdersId);
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo((byte) orderStatus);
        int count = ordersMapper.updateByExample(orders, example);
        return count;
    }

    //修改订单状态
    public int updateOrderStatus(String orderNo, OrdersConstants orderStatus, OrdersConstants payStatus) {
        int count = ordersMapper.updateOrderStatus(orderNo,orderStatus.getStatusNo(),payStatus.getStatusNo());
        return count;
    }


    //查询input_time
    public OrderLogisticsEntity queryByOrderChild(Long OrderChildId) {
        return orderLogisticsDao.getOrderLogisticsEntitiesByOrderId(OrderChildId);
    }

    //创建子订单

    public Optional<OrderChild> createOrderChild(OrderChildGenerate orderChildGenerate) {
            //  獲取主訂單  数据
            String mainNo = orderChildGenerate.getMainNo();
            OrderChildDto orderChildDto = ordersMapper.queryOrderChildDtoByOrderNo(mainNo);
            if(!Stream.of((byte)2,(byte)5).anyMatch( integer -> integer.equals(orderChildDto.getOrderStatus()))){
                throw  new RuntimeException("该订单未支付");
            }
            if(Objects.isNull(orderChildDto)){
                throw  new RuntimeException("未检测到相关"+orderChildGenerate.getMainNo());
            }
            Long mainId = orderChildDto.getOrderId();
            String generate = IdGenerate.generate(IdGenerate.SCHILD_ORDER_NO);
            Long id = SnowflakeId.getId();
            LocalDateTime salesReturnTime = orderChildDto.getSalesReturnTime();
            OrderChild build = OrderChild.builder()
                        .id(id)
                        .orderNo(generate)
                        .mainId(mainId)
                        .explain(orderChildGenerate.getExplanation())
                        .goodsCount(orderChildDto.getGoodsCount())
                        .price(orderChildDto.getSellPrice())
                        .status((byte) 6)
                        .isReturn(orderChildDto.getIsReturn())
                        .remark(orderChildGenerate.getRemarks())
                        .build();
            if(salesReturnTime!=null){
                build.setSalesReturnTime(Date.from(salesReturnTime.atZone( ZoneId.systemDefault()).toInstant()));
            }
            insertOrderChild(build);
            String logisticsCompany = orderChildGenerate.getLogisticsCompany();
            String s = jedisCluster.get(redisParam.getCourierCompanyCode() + logisticsCompany);
            if(StringUtils.isBlank(s)){
                throw  new RuntimeException("請輸入正確額快遞公司"+logisticsCompany);
            }
            orderLogisticsDao.save(OrderLogisticsInsertPojo.builder().goodsCount(orderChildDto.getGoodsCount()).goodsName(orderChildDto.getSkuName()).trackingNumber(orderChildGenerate.getTrackingNumber()).dhl(logisticsCompany) .orderId(id).build());
            ordersMapper.updateByOrderNo(mainNo,(byte) 6,LocalDateTime.now());
            return Optional.ofNullable(build);


    }

    @Override
    public OrderChild createOrderChild(Long orderId, String explain, int goodsCount, String remark) {
        return null;
    }

    //插入子订单
    public void insertOrderChild(OrderChild orderChild) {
        orderChildMapper.insertSelective(orderChild);
    }

    /*订单列表查询
     * @author zouqiang
     * @param
     * @return
     */
    public Result queryOrdersList(OrdersPageQuery query) {
        try {
            Integer pageNumKey = query.getPageNumKey() == 0 ? 1 : query.getPageNumKey();
            Integer pageSizeKey = query.getPageSizeKey();
            Page page = PageHelper.startPage(pageNumKey, pageSizeKey);

            //当前所有商户下的
            //获取当前登陆的用户所在的商户
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            Map<String, Object> map = new HashMap<>();
            map.put("userId", (long) operator.getId());
            List<OrdersResult> ordersResults = ordersMapper.queryListByRecord(map);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("ordersResults", ordersResults);
            data.put("operatorname", operator.getName());
            PageInfo<OrdersResult> pageInfo = new PageInfo<>(ordersResults);
            data.put("total", pageInfo.getTotal());
            data.put("pageNum", pageNumKey);
            return Result.ok(data);

        } catch (Exception e) {
            LOGGER.error(">>>>>>商品后台管理，分页查询商品列表，异常：", e);
            return Result.error("分页查询商品列表失败：系统异常");
        }
    }

    public Result queryOrdersListBy(OrdersPageQuery query) {
        try {
            Integer pageNumKey = query.getPageNumKey() == 0 ? 1 : query.getPageNumKey();
            Integer pageSizeKey = query.getPageSizeKey();
            Page page = PageHelper.startPage(pageNumKey, pageSizeKey);
            Byte orderStatus = query.getOrdersStatus();
            try {
                //当前所有商户下的
                //获取当前登陆的用户所在的商户
                Operator operator = PermissionInfoUtil.getCurrentLogginUser();
                Map<String, Object> map = new HashMap<>();
                map.put("userId", (long) operator.getId());
                map.put("orderStatus", 11 == orderStatus ? null : orderStatus);
                map.put("orderNo", query.getOrderNo());
                map.put("consumerName", query.getConsumerName());
                map.put("telephone", query.getTelephone());
                DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                if(StringUtils.isNotBlank(query.getstartUpdate())) {
                    LocalDate startUpdate = LocalDate.parse(query.getstartUpdate(), f);
                    LocalDateTime.of(startUpdate, LocalTime.MIN);
                    map.put("startUpdate", startUpdate);
                }
                if(StringUtils.isNotBlank(query.getendUpdate())) {
                    LocalDate endUpdate = LocalDate.parse(query.getendUpdate(), f);
                    LocalDateTime.of(endUpdate, LocalTime.MIN);
                    map.put("endUpdate", endUpdate);
                }
                List<OrdersResult> ordersResults = ordersMapper.queryListByRecord(map);
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("ordersResults", ordersResults);
                data.put("operatorname", operator.getName());
                PageInfo<OrdersResult> pageInfo = new PageInfo<>(ordersResults);
                data.put("total", pageInfo.getTotal());
                data.put("pageNum", pageNumKey);
                return Result.ok(data);
            } catch (Exception e) {
                LOGGER.error(">>>>>>时间转换异常：", e);
                return Result.error("时间转换异常：系统异常");
            }
        } catch (Exception e) {
            LOGGER.error(">>>>>>商品后台管理，分页查询商品列表，异常：", e);
            return Result.error("分页查询商品列表失败：系统异常");
        }
    }
    @Override
    public Orders selectByOrderNo(String orderNo) {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        return ordersMapper.selectByOrderNo(example);
    }

    @Override
    public int updateByPrimaryKeySelective(Orders orders) {
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public Orders selectByPrimaryKey(long id) {
        return ordersMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public void ReturnGoods(Orders orders) {
        orders.setIsReturn(true);
        //同意退货时间
        Date date =new Date();
        orders.setSalesReturnTime(date);
        orders.setOrderStatus(Status.SaleReturned);
        orders.setLastUpdate(date);
        ordersMapper.updateByPrimaryKeySelective(orders);
        OrderChildExample oe = new OrderChildExample();
        List<OrderChild> orderChildrens = orderChildMapper.selectByMainId(orders.getId());
        orderChildrens.forEach(orderChildren -> {
            orderChildren.setIsReturn(true);
            orderChildren.setStatus(Status.SaleReturned);
            orderChildren.setSalesReturnTime(date);
            orderChildMapper.updateByPrimaryKeySelective(orderChildren);
        });

    }

    @Override
    public long orderTimeout(String orderNo) {
        return ordersMapper.orderTimeout(orderNo);
    }

    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public int updateOrderStatusTimeOut() {
        return ordersMapper.updateOrderStatusTimeOut();
    }

    @Override
    public List<Orders> initOrderToRedis(Long id) {
        return ordersMapper.initOrderToRedis(id);
    }

    @Override
    public int updateOrderReturnStatus(String orderNo, OrdersConstants ordersConstants) {
        return ordersMapper.updateOrderReturnStatus(orderNo,ordersConstants.getStatusNo());
    }

    @Override
    public void updateReturnStatus(String orderNo, ReturnOfGoodsConstants systemRefund) {
        ordersMapper.updateReturnStatus(orderNo,systemRefund.ordinal());
    }

}
