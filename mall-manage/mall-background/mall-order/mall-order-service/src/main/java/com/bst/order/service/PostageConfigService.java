package com.bst.order.service;

import com.bst.common.modle.Result;
import com.bst.common.pojo.Pagination;
import com.bst.common.modle.order.PostageConfigPojo;
import com.bst.common.modle.order.PostageConfigInsertPojo;

import java.util.Map;

/**
 * 
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-18 16:10:33
 */
public interface PostageConfigService {

	Result queryObject(Long id);
    //
	Result queryByProvince(String name);

	Result queryList(Pagination pagination, String like);

	Result queryTotal(Map<String, Object> map);
	
	Result save(PostageConfigInsertPojo postageConfig);


	Result update(PostageConfigPojo postageConfig);

	Result delete(Long id);

	Result deleteBatch(Long[] ids);
}
