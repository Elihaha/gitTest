package com.bst.goods.service;

import com.bst.common.entity.goods.GoodsSpecEntity;
import com.bst.common.entity.goods.GoodsSpecValueEntity;
import com.bst.common.modle.Result;

import java.util.List;
import java.util.Map;

/**
 * 规格表
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-10-26 09:45:33
 */
public interface GoodsSpecService {
	
	GoodsSpecEntity queryObject(Long id);

	Result queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(GoodsSpecEntity goodsSpec);

	void update(GoodsSpecEntity goodsSpec);

	void delete(Long id);

	void deleteBatch(Long[] ids);
}
