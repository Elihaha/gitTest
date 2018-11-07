package com.bst.common.mapper.goods;


import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.entity.goods.GoodsSpuExample;
import com.bst.common.modle.goods.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsSpuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpu record);

    int insertSelective(GoodsSpu record);

    GoodsSpu selectByPrimaryKey(Long id);
    List<GoodsRedisInfo>  selectRedisInfoByPrimaryKey(@Param("spuNo") String spuNo, @Param("shopId") Long shopId);

    int updateByPrimaryKeySelective(GoodsSpu record);

    int updateByPrimaryKeyWithBLOBs(GoodsSpu record);

    int updateByPrimaryKey(GoodsSpu record);

    int countByExample(GoodsSpuExample example);

    int deleteByExample(GoodsSpuExample example);

    List<GoodsSpu> selectByExampleWithBLOBs(GoodsSpuExample example);

    List<GoodsSpu> selectByExample(GoodsSpuExample example);

    int updateByExampleSelective(@Param("record") GoodsSpu record, @Param("example") GoodsSpuExample example);

    int updateByExampleWithBLOBs(@Param("record") GoodsSpu record, @Param("example") GoodsSpuExample example);

    int updateByExample(@Param("record") GoodsSpu record, @Param("example") GoodsSpuExample example);

    List<GoodsResult> queryListByRecord(@Param("query") GoodsQuery query);

    /**获取状态除  逻辑删除  之外所有的  GoodsSpu*/
    List<GoodsSpu> selectGoodsSpuAll();

    int updateBatchById(@Param("list") List<GoodsSpu> list);

    @Select("   call update_spu(#{spuNo,mode=IN},#{number,mode=IN,jdbcType=INTEGER}) ")
    @Options(statementType = StatementType.CALLABLE)
    void updateBatchBySpuNo(Map map);

    GoodsPicNameQuery queryPicAndName(String goodsSkuNo);

    List<GoodsSpuCategoryResponse> queryByCategoryIdAndShopId(@Param("categoryId") Long  categoryId,
                                                              @Param("shopId") Long shopId,
                                                              @Param("imageType")Byte imageType,
                                                              @Param("goodsStatus")Byte goodsStatus);

    List<GoodsSpu> queryAllSpuByShopId(Long shopId);
}