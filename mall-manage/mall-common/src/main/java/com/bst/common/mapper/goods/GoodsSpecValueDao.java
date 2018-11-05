package com.bst.common.mapper.goods;

import com.bst.common.entity.goods.GoodsSpecValueEntity;
import com.bst.common.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 规格值
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-10-26 09:45:33
 */
@Mapper
public interface GoodsSpecValueDao extends BaseDao<GoodsSpecValueEntity> {


    /**
     *
     * @param id  规格id
     * @return 该规格对应的所有规格值
     *
     */
    List<GoodsSpecValueEntity>  queryListSpecValue(Object id);

    /**
     * 新增规格值集合
     * @param params 规格值集合
     */
    void saveSpecValueList(List<Map<String,Object>> params);
	
}
