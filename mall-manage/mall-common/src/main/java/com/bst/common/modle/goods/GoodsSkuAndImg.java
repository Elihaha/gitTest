package com.bst.common.modle.goods;

import com.alibaba.fastjson.JSONObject;
import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.entity.goods.GoodsSpecValueEntity;
import lombok.Builder;
import lombok.Data;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;


@Data
public class GoodsSkuAndImg extends GoodsSku {

    private String spuNo;

    private String imgPath;

    private String info;

    private List<GoodsSpecValueEntity> goodsSpecValueEntity;

    public String getInfo() {
        return JSONObject.toJSONString(new HashMap<String, String>() {{
            put("pricing", getPricing().toString());
            put("imgPath", getImgPath());
            put("skuNo", getSkuNo());
        }});
    }
}
