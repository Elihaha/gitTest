package com.bst.common.mapper.order;

import com.bst.common.entity.order.CourierCompany;

import java.util.List;
import java.util.Map;

public interface CourierCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourierCompany record);

    int insertSelective(CourierCompany record);

    CourierCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourierCompany record);

    int updateByPrimaryKey(CourierCompany record);


    List<CourierCompany> list(Map<String,Object> stringObjectMap);
}