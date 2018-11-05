package com.bst.common.mapper.pay;

import com.bst.common.entity.pay.OrderPayRecord;

public interface OrderPayRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderPayRecord record);

    int insertSelective(OrderPayRecord record);

    OrderPayRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderPayRecord record);

    int updateByPrimaryKey(OrderPayRecord record);
}