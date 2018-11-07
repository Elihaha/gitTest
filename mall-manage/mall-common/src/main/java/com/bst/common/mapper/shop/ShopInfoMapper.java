package com.bst.common.mapper.shop;

import java.util.List;

import com.bst.common.entity.shop.QueryShop;
import com.bst.common.entity.shop.ShopInfo;
import com.bst.common.entity.shop.ShopInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShopInfoMapper {
    long countByExample(ShopInfoExample example);

    int deleteByExample(ShopInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopInfo record);

    int insertSelective(ShopInfo record);

    List<ShopInfo> selectByExample(ShopInfoExample example);

    ShopInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopInfo record, @Param("example") ShopInfoExample example);

    int updateByExample(@Param("record") ShopInfo record, @Param("example") ShopInfoExample example);

    int updateByPrimaryKeySelective(ShopInfo record);

    int updateByPrimaryKey(ShopInfo record);

    ShopInfo selectShopInfoByPermissionId(Integer permissionId);

    List<ShopInfo> queryPage(QueryShop query);

    List<ShopInfo> selectAllInfo();
}