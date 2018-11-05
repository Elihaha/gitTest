package com.bst.common.mapper.goods;


import com.bst.common.entity.goods.GoodsSpuSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsSpuSpecMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpuSpec record);

    int insertSelective(GoodsSpuSpec record);

    GoodsSpuSpec selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSpuSpec record);

    int updateByPrimaryKey(GoodsSpuSpec record);

    void insertByList(List<GoodsSpuSpec> list);
}