package com.bst.common.modle.goods;

import com.alibaba.fastjson.JSON;
import com.bst.common.entity.goods.GoodsSpecEntity;
import com.bst.common.entity.goods.GoodsSpecValueEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/11/1 16:29 2018 11
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecInfo extends GoodsSpecEntity {

    List<GoodsSpecValueEntity> goodsSpecValueEntities;


    public List<GoodsSpecValueEntity> getGoodsSpecValueEntities() {
        return goodsSpecValueEntities;
    }

    public void setGoodsSpecValueEntities(List<GoodsSpecValueEntity> goodsSpecValueEntities) {
        this.goodsSpecValueEntities = goodsSpecValueEntities;
    }
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
