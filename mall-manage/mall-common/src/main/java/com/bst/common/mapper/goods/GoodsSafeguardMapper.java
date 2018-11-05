package com.bst.common.mapper.goods;


import com.bst.common.entity.goods.GoodsSafeguard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSafeguardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSafeguard record);

    int insertSelective(GoodsSafeguard record);

    GoodsSafeguard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSafeguard record);

    int updateByPrimaryKey(GoodsSafeguard record);
}