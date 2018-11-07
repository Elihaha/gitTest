package com.bst.common.mapper.goods;

import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.entity.goods.GoodsSkuExample;
import com.bst.common.modle.goods.GoodsSkuAndImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSku record);

    int insertSelective(GoodsSku record);

    GoodsSku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSku record);

    int updateByPrimaryKey(GoodsSku record);

    int countByExample(GoodsSkuExample example);

    int deleteByExample(GoodsSkuExample example);

    List<GoodsSku> selectByExample(GoodsSkuExample example);

    int updateByExampleSelective(@Param("record") GoodsSku record, @Param("example") GoodsSkuExample example);

    int updateByExample(@Param("record") GoodsSku record, @Param("example") GoodsSkuExample example);

    //自定义--zouqiang
    List<GoodsSku> selectByspuId(Long spuId);


      List<GoodsSku> selectGoodsSkuAll();

      List<GoodsSkuAndImg> selectGoodsSkuAllAndImg(@Param("spuNo") String spuNo, @Param("shopId") Long shopId);

    int updateBatchBySpuId(@Param("list") List<GoodsSku> list);

    @Select("  call update_sku(#{skuNo,mode=IN},#{number,mode=IN,jdbcType=INTEGER})")
    @Options(statementType = StatementType.CALLABLE)
    void updateBatchBySkuNo(Map map);

    List<Long> getSkuIdsBySpuId(Long spuId);

    int insertGoodsSkuByList(List<GoodsSku> list);

    int updateSkuTotalAndPriceByList(List<GoodsSku> list);

    GoodsSku selectSkuBySkuNo(String skuNo);
}