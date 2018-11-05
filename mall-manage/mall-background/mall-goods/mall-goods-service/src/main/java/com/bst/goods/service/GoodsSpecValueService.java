package com.bst.goods.service;


import com.bst.common.entity.goods.GoodsSpecValueEntity;

import java.util.List;
import java.util.Map;

public interface GoodsSpecValueService {



    /**
     * @param id  规格id
     * @return 该规格的所有的规格值
     *
     */
    List<GoodsSpecValueEntity> queryEntityValue(Long id);


    /**
     * @param params  规格值集合
     *  同时存入一个规格集合值 或者一个单个集合
     */
    void saveSpecValueList(List<Map<String,Object>> params);

}
