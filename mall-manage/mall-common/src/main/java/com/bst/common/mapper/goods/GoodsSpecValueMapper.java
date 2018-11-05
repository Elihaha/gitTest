package com.bst.common.mapper.goods;


import com.bst.common.entity.goods.GoodsSpecValueEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsSpecValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpecValueEntity record);

    int insertSelective(GoodsSpecValueEntity record);

    GoodsSpecValueEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSpecValueEntity record);

    int updateByPrimaryKey(GoodsSpecValueEntity record);


}