package com.bst.scheduled.service.impl;

import com.bst.common.entity.order.CourierCompany;
import com.bst.common.mapper.order.CourierCompanyMapper;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/30 9:40 2018 09
 */
@Component
public class CourierCompanyTask implements Runnable  {

    @Autowired
    RedisParam redisParam;

    @Autowired
    CourierCompanyMapper courierCompany;

    @Autowired
    JedisClusterUtils jedisCluster;




    @Override
    public void run() {
        ///   快递公司配置

        List<CourierCompany> list = courierCompany.list(new HashMap<>());
        list.forEach(courierCompany1 -> {
            final String code = courierCompany1.getCode();
            final String name = courierCompany1.getName();
            final String courierCompanyCode = redisParam.getCourierCompanyCode();
            final String courierCompanyName = redisParam.getCourierCompanyName();

            if (StringUtils.isNoneBlank(code,name,courierCompanyCode,courierCompanyName)) {
                jedisCluster.setnx(courierCompanyCode + code, name);
                jedisCluster.setnx(courierCompanyName + name, code);
            }

        });
    }
}
