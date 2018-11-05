package com.bst.common.mapper.goods;

import com.bst.common.entity.goods.GoodsSpecEntity;
import com.bst.common.entity.goods.GoodsSpecValueEntity;
import com.bst.common.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 规格表
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-10-26 09:45:33
 */
@Mapper
public interface GoodsSpecDao extends BaseDao<GoodsSpecEntity> {

    @Override
    List<GoodsSpecEntity> queryList(Map<String,Object> ma);

    @Override
    void save(GoodsSpecEntity goodsSpecEntity);

}
