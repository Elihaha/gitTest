package com.bst.common.mapper.order;

import com.bst.common.entity.order.OrderChild;
import com.bst.common.entity.order.OrderChildExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderChildMapper {
    long countByExample(OrderChildExample example);

    int deleteByExample(OrderChildExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderChild record);

    int insertSelective(OrderChild record);

    List<OrderChild> selectByExample(OrderChildExample example);

    OrderChild selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderChild record, @Param("example") OrderChildExample example);

    int updateByExample(@Param("record") OrderChild record, @Param("example") OrderChildExample example);

    int updateByPrimaryKeySelective(OrderChild record);

    int updateByPrimaryKey(OrderChild record);

    OrderChild selectByOrderNo(String orderNo);
    int updateByOrderNo(@Param("orderNo") String orderNo, @Param("status") byte status);

    List<OrderChild> selectByMainId(Long mainId);
}