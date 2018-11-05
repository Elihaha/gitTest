package com.bst.user.permission.mvc.impl;

import com.bst.backcommon.permission.entity.Operator;
import com.bst.user.permission.mvc.mapper.OperatorMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorServiceImpl implements IOperatorService {
    @Autowired
    private OperatorMapper operatorMapper;

    public Operator getOperator(){
        return (Operator) SecurityUtils.getSubject().getPrincipal();
    }

    @Override
    public Operator getById() {
        Operator operator = (Operator) SecurityUtils.getSubject().getPrincipal();
        operator=operatorMapper.getById(operator.getId());
        return operator;
    }
}
