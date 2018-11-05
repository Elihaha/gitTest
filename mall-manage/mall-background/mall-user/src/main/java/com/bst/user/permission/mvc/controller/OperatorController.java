package com.bst.user.permission.mvc.controller;


import com.bst.backcommon.permission.entity.Operator;
import com.bst.common.modle.Result;
import com.bst.user.permission.mvc.impl.IOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("operator")
public class OperatorController {
    @Autowired
    private IOperatorService operatorService;
    @GetMapping("getOperator")
    public Object getOperator(){
       Operator operator= operatorService.getById();
       return Result.ok(operator);
    }
}
