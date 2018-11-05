package com.bst.common.modle.order;

import com.alibaba.fastjson.JSON;
import com.bst.common.config.Snowflake.SnowflakeId;
import com.bst.common.entity.order.PostageConfigEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel
@Builder
public class PostageConfigInsertPojo {


    //省份 运费计算是基于省到省的
    @ApiModelProperty(name = "province", value = "长沙" ,example = "成都")
    private String province;
    //
    @ApiModelProperty(name = "province", value = "邮费",example = "48.99")
    private BigDecimal postage;
    @ApiModelProperty(name = "province", value = "状态",example = "0")
    private Integer status;


    public PostageConfigInsertPojo() {
    }

    public PostageConfigInsertPojo(String province, BigDecimal postage, Integer status) {
        this.province = province;
        this.postage = postage;
        this.status = status;
    }



    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public PostageConfigEntity castPostageConfigEntity(Integer id,Long shopId) {
        PostageConfigEntity build = PostageConfigEntity.builder()
                .id(SnowflakeId.getId()+"")
                .status(this.getStatus())
                .createTime( LocalDateTime.now())
                .lastUpdateTime(LocalDateTime.now())
                .lastUpdateUserId(id.longValue())
                .province(this.getProvince())
                .postage(this.getPostage())
                .shopId(shopId)
                .build();
        return build;


    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
