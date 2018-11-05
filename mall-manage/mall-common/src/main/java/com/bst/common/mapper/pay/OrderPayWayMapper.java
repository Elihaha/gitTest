package com.bst.common.mapper.pay;

import com.bst.common.entity.pay.Payment;

import java.util.List;

public interface OrderPayWayMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(Payment record);

    int insertSelective(Payment record);

    Payment selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);

    List<Payment> selectAll();
}