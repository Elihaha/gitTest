package com.bst.common.mapper.goods;


import com.bst.common.entity.goods.GoodsSpecEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSpecMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpecEntity record);

    int insertSelective(GoodsSpecEntity record);

    GoodsSpecEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSpecEntity record);

    int updateByPrimaryKey(GoodsSpecEntity record);
}