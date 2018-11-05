package com.bst.common.mapper.goods;


import com.bst.common.entity.goods.GoodsSkuSafeguard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSkuSafeguardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSkuSafeguard record);

    int insertSelective(GoodsSkuSafeguard record);

    GoodsSkuSafeguard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSkuSafeguard record);

    int updateByPrimaryKey(GoodsSkuSafeguard record);
}