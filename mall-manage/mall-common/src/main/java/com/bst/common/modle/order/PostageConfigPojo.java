package com.bst.common.modle.order;

import com.alibaba.fastjson.JSON;
import com.bst.common.config.Snowflake.SnowflakeId;
import com.bst.common.entity.order.PostageConfigEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Builder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@ApiModel
@Builder
public class PostageConfigPojo {

    @ApiModelProperty(name = "id",example = "491943698590859264" )
    private String id;

    //省份 运费计算是基于省到省的
    @ApiModelProperty(name = "province", value = "长沙" ,example = "成都")
    private String province;
    //
    @ApiModelProperty(name = "province", value = "邮费",example = "48.99")
    private BigDecimal postage;
    @ApiModelProperty(name = "province", value = "状态",example = "0")
    private Integer status;


    public PostageConfigPojo() {
    }

    public PostageConfigPojo(String id, String province, BigDecimal postage, Integer status) {
        this.id = id;
        this.province = province;
        this.postage = postage;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public PostageConfigEntity castPostageConfigEntity(Long id,Long shopid) {
        PostageConfigEntity build = PostageConfigEntity.builder()
                .status(this.getStatus())
                .id(this.getId()!=null?this.getId(): SnowflakeId.getId()+"")
                .createTime( LocalDateTime.now())
                .lastUpdateTime(LocalDateTime.now())
                .lastUpdateUserId(id.longValue())
                .province(this.getProvince())
                .postage(this.getPostage())
                .shopId(shopid.longValue())
                .build();
        return build;


    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
