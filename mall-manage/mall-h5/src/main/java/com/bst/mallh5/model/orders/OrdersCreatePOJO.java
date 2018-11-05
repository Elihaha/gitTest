package com.bst.mallh5.model.orders;

import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.entity.order.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/10/22 11:58 2018 10
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrdersCreatePOJO {
    private int count;
    private Orders nOrders;
    private BigDecimal sellPrice;
    private Long shopId;
    private String signerPhone;
    private String province;
}
