package com.bst.common.modle.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/28 20:12 2018 09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderChildDto {

       private  Long orderId;
       private  Byte orderStatus;
       private  Boolean isReturn;
       private  Integer goodsCount;
       private LocalDateTime salesReturnTime;
       private  BigDecimal sellPrice;
       private  String  skuName;

//    String key = goodsSpuBaseInfoKey.replaceAll("_",":")+":"+goodsId;

}
