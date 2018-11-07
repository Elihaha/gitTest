package com.bst.common.mapper.goods;


import com.bst.common.entity.goods.GoodsImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface GoodsImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsImage record);

    int insertSelective(GoodsImage record);

    GoodsImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsImage record);

    int updateByPrimaryKey(GoodsImage record);

    String selectByRecode(GoodsImage recode);

    int insertBatchImages(List<GoodsImage> images);

    int deleteByMainNo(String mainNo);

    int insertAndUpdateByMainNo(List<GoodsImage> images);

    List<String> queryUrlBySkuNo(List<String> list);
}