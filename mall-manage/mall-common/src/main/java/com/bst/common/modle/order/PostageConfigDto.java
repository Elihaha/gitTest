package com.bst.common.modle.order;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-18 16:10:33
 */
@Builder
@ApiModel
public class PostageConfigDto implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private String id;
    //省份 运费计算是基于省到省的
    private String province;
    //
    private BigDecimal postage;
    //
    private String createTime;
    //
    private String lastUpdateTime;
    //最后修改的人
    private String lastUpdateUserId;
    //
    private Integer status;

    private Long shopId;

    /**
     * 设置：id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取：id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置：省份 运费计算是基于省到省的
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：省份 运费计算是基于省到省的
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置：
     */
    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    /**
     * 获取：
     */
    public BigDecimal getPostage() {
        return postage;
    }

    /**
     * 设置：
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取：
     */
    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置：最后修改的人
     */
    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    /**
     * 获取：最后修改的人
     */
    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    /**
     * 设置：
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：
     */
    public Integer getStatus() {
        return status;
    }

    public PostageConfigDto() {
    }

    ;

    public PostageConfigDto(String id, String province, BigDecimal postage, String createTime, String lastUpdateTime, String lastUpdateUserId, Integer status, Long shopId) {
        this.id = id;
        this.province = province;
        this.postage = postage;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
        this.lastUpdateUserId = lastUpdateUserId;
        this.status = status;
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    ;

}
