package com.bst.goods.service.impl;

import com.bst.common.entity.goods.GoodsSpecValueEntity;
import com.bst.common.mapper.goods.GoodsSpecValueMapper;
import com.bst.goods.service.GoodsSpecValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service("goodsSpecValueService")
public class GoodsSpecValueServiceImpl implements GoodsSpecValueService {


    @Autowired
    private GoodsSpecValueMapper goodsSpecValueMapper;


    @Override
    public List<GoodsSpecValueEntity> queryEntityValue(Long id) {
        return goodsSpecValueMapper.queryListSpecValue(id);
    }

    @Override
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    public void saveSpecValueList(List<Map<String,Object>> params) {
        //存入规格值参数
        goodsSpecValueMapper.saveSpecValueList(params);
    }

}
