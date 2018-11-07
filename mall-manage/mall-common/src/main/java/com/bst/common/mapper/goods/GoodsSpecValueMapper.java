package com.bst.common.mapper.goods;


import com.bst.common.entity.goods.GoodsSpecValueEntity;
import com.bst.common.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsSpecValueMapper extends BaseDao<GoodsSpecValueEntity> {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpecValueEntity record);

    int insertSelective(GoodsSpecValueEntity record);

    GoodsSpecValueEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSpecValueEntity record);

    int updateByPrimaryKey(GoodsSpecValueEntity record);


    /**
     *
     * @param id  规格id
     * @return 该规格对应的所有规格值
     *
     */
    List<GoodsSpecValueEntity>  queryListSpecValue(Object id);

    /**
     * 新增规格值集合
     * @param params 规格值集合
     */
    void saveSpecValueList(List<Map<String,Object>> params);

}