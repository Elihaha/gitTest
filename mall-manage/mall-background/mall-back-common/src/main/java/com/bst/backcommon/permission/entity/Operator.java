package com.bst.backcommon.permission.entity;

import com.bst.common.entity.shop.ShopInfo;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Operator implements Serializable,AuthCachePrincipal {
    private Integer id;

    private String name;

    private String password;

    private Integer statue;

    private String email;

    private String phone;

    private Date addTime;

    private String remark;

    private String salt;

    private String macroId;

    private Integer creator;

    private Integer updator;

    private String headUrl;

    private Integer points;

    private Integer type;

    private Integer userColumn;

    private Integer parentId;

    private String identitys;

    private String grades;

    private String citys;

    private Integer verify;

    private Integer cid;

    private Integer manageFlag;

    private ShopInfo shopInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getMacroId() {
        return macroId;
    }

    public void setMacroId(String macroId) {
        this.macroId = macroId == null ? null : macroId.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserColumn() {
        return userColumn;
    }

    public void setUserColumn(Integer userColumn) {
        this.userColumn = userColumn;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIdentitys() {
        return identitys;
    }

    public void setIdentitys(String identitys) {
        this.identitys = identitys == null ? null : identitys.trim();
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades == null ? null : grades.trim();
    }

    public String getCitys() {
        return citys;
    }

    public void setCitys(String citys) {
        this.citys = citys == null ? null : citys.trim();
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getManageFlag() {
        return manageFlag;
    }

    public void setManageFlag(Integer manageFlag) {
        this.manageFlag = manageFlag;
    }

    @Override
    public String getAuthCacheKey() {
        return this.getName();
    }

    public ShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return Objects.equals(id, operator.id) &&
                Objects.equals(name, operator.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}