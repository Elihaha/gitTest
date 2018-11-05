package com.bst.common.mapper.order;

import com.bst.common.entity.order.OrderPostageEntity;
import com.bst.common.utils.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 运费配置
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-18 16:10:33
 */
@Mapper
public interface OrderPostageDao extends BaseDao<OrderPostageEntity> {
	
}
