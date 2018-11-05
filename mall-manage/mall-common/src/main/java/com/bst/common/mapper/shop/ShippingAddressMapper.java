package com.bst.common.mapper.shop;

import com.bst.common.entity.shop.ShippingAddress;

public interface ShippingAddressMapper {
    int insert(ShippingAddress record);

    int insertSelective(ShippingAddress record);

    ShippingAddress queryByOrderId(Long orderId);
}