package com.bst.backcommon.permission;

import com.bst.backcommon.permission.entity.Operator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 * @author gan
 * @desc:  权限信息工具类
 */
public class PermissionInfoUtil {
    /**
     * @desc:  获取当前登陆用户的用户
     * @param
     * @return:
     */
    public static Operator getCurrentLogginUser(){
        Subject subject = SecurityUtils.getSubject();
        return (Operator) subject.getPrincipal();
    }

}
