package com.bst.user.permission.mvc.mapper;


import com.bst.user.permission.mvc.entity.SysOperatorMenu;
import com.bst.user.permission.mvc.entity.SysOperatorMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysOperatorMenuMapper {
    long countByExample(SysOperatorMenuExample example);

    int deleteByExample(SysOperatorMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysOperatorMenu record);

    int insertSelective(SysOperatorMenu record);

    List<SysOperatorMenu> selectByExample(SysOperatorMenuExample example);

    SysOperatorMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysOperatorMenu record, @Param("example") SysOperatorMenuExample example);

    int updateByExample(@Param("record") SysOperatorMenu record, @Param("example") SysOperatorMenuExample example);

    int updateByPrimaryKeySelective(SysOperatorMenu record);

    int updateByPrimaryKey(SysOperatorMenu record);

    void deleteByOpId(Long id);
}