package com.bst.common.mapper.goods;


import com.bst.common.entity.goods.GoodsSkuSpecValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsSkuSpecValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSkuSpecValue record);

    int insertSelective(GoodsSkuSpecValue record);

    GoodsSkuSpecValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSkuSpecValue record);

    int updateByPrimaryKey(GoodsSkuSpecValue record);

    int insertGoodsSkuSpecValueByList(List<GoodsSkuSpecValue> list);
}