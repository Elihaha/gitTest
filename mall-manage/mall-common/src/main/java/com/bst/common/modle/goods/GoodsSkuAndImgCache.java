package com.bst.common.modle.goods;

import com.alibaba.fastjson.JSONObject;
import com.bst.common.entity.goods.GoodsSku;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Builder
@Data
public class GoodsSkuAndImgCache {

    private String spuNo;

    private String imgPath;

    private String info;

}
