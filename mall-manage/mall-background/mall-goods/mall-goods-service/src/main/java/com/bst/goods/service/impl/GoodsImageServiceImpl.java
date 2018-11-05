package com.bst.goods.service.impl;

import com.bst.common.mapper.goods.GoodsImageMapper;
import com.bst.goods.service.GoodsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lumin
 * @description:
 * @create 2018-09-18 10:46
 **/
@Service("goodsImageService")
public class GoodsImageServiceImpl implements GoodsImageService {

    @Autowired
    private GoodsImageMapper goodsImageMapper;

}
