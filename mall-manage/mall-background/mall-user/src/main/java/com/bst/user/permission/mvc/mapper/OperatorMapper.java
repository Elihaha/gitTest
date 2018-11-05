package com.bst.user.permission.mvc.mapper;

import com.bst.backcommon.permission.entity.Operator;
import com.bst.backcommon.permission.entity.OperatorExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperatorMapper {
    long countByExample(OperatorExample example);

    int deleteByExample(OperatorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Operator record);

    int insertSelective(Operator record);

    List<Operator> selectByExample(OperatorExample example);

    Operator selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Operator record, @Param("example") OperatorExample example);

    int updateByExample(@Param("record") Operator record, @Param("example") OperatorExample example);

    int updateByPrimaryKeySelective(Operator record);

    int updateByPrimaryKey(Operator record);

    List<Operator> selectVerifier(@Param("verifyMark") String verifyMark);

    Integer isTransfer(@Param("transferMark") String transferMark, @Param("operaId") Integer operaId);

    Operator getById(Integer id);

    List<Operator> selectByParentId(Integer mainId);

    Integer getCountByParentId(Integer mainId);

    /*AdminAccountInfo findAdminAccountInfoByMainId(Integer currentLogginUserMasterAccountId);

    Integer getCountByParentIdAndManageFlag(ParentAndManageModel param);

    List<Operator> selectByParentIdAndManageFlag(ParentAndManageModel param);

    List<Operator> findByUnitAndValue(UnitAndValueModel unitAndValueModel);

    List<Operator> findByUnitAndValueNotId(UnitAndValueModel email);*/
}