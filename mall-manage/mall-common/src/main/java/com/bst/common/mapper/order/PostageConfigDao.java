package com.bst.common.mapper.order;

import com.bst.common.entity.order.PostageConfigEntity;
import com.bst.common.modle.order.PostageConfigDto;
import com.bst.common.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-18 16:10:33
 */
@Mapper
public interface PostageConfigDao extends BaseDao<PostageConfigEntity> {
       //   根据城市名获取 邮费相关信息
      PostageConfigEntity queryByProvince(@Param("name") String name, @Param("shopId") Long shopId);

      List<PostageConfigDto> queryPostageConfigPojoList(Map<String, Object> map);

    //   根据城市名获取 邮费相关信息
    BigDecimal queryByGoodsIdAndAddress(@Param("address") String address, @Param("goodsId") Long goodsId);


}
