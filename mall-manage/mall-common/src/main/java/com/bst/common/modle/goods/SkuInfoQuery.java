package com.bst.common.modle.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuInfoQuery {

    private String skuNo;

    private String imgPath;

    private String price;

    private String info;

    private Integer stock;

}
