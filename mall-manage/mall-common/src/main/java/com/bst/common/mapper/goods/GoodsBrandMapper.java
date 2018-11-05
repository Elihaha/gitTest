package com.bst.common.mapper.goods;


import com.bst.common.entity.goods.GoodsBrand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsBrand record);

    int insertSelective(GoodsBrand record);

    GoodsBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsBrand record);

    int updateByPrimaryKey(GoodsBrand record);
}