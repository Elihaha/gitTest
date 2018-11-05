package com.bst.mallh5.service.order.impl;

import com.bst.common.mapper.order.PostageConfigDao;
import com.bst.common.modle.Result;
import com.bst.mallh5.service.order.PostageConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("postageConfigService")
@Slf4j
@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class PostageConfigServiceImpl implements PostageConfigService {
    @Autowired
    private PostageConfigDao postageConfigDao;


    @Override
    public Result queryByProvince(String name, Long shopid) {
        try {
            return Result.ok(postageConfigDao.queryByProvince(name, shopid));
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }


}
