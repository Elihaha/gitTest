package com.bst.common.mapper.goods;


import com.bst.common.entity.goods.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    List<GoodsCategory> selectAll(Long isChild);

    List<GoodsCategory> queryListByRecord(Map map);

    List<GoodsCategory> queryListByCateGory(GoodsCategory goodsCategory);

    int deleteByPid(Long pid);

    List<GoodsCategory> queryListByPid(@Param("pid") Long pid,@Param("shopId") Long shopId);
}