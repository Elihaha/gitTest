package com.bst.common.entity.goods;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;


/**
 * 规格值
 *
 * @author pengzhen
 * @email pengzhen
 * @date 2018-10-26 09:45:33
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsSpecValueEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //主键
        private Long id;
            //规格id
        private Long specId;
            //规格值
        private String specValue;
            //创建时间
        private LocalDateTime gmtCreate;
            //更新时间
        private LocalDateTime gmtUpdate;
    
            /**
         * 设置：主键
         */
        public void setId(Long id) {
            this.id = id;
        }

        /**
         * 获取：主键
         */
        public Long getId() {
            return id;
        }
            /**
         * 设置：规格id
         */
        public void setSpecId(Long specId) {
            this.specId = specId;
        }

        /**
         * 获取：规格id
         */
        public Long getSpecId() {
            return specId;
        }
            /**
         * 设置：规格值
         */
        public void setSpecValue(String specValue) {
            this.specValue = specValue;
        }

        /**
         * 获取：规格值
         */
        public String getSpecValue() {
            return specValue;
        }
            /**
         * 设置：创建时间
         */
        public void setGmtCreate(LocalDateTime gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        /**
         * 获取：创建时间
         */
        public LocalDateTime getGmtCreate() {
            return gmtCreate;
        }
            /**
         * 设置：更新时间
         */
        public void setGmtUpdate(LocalDateTime gmtUpdate) {
            this.gmtUpdate = gmtUpdate;
        }

        /**
         * 获取：更新时间
         */
        public LocalDateTime getGmtUpdate() {
            return gmtUpdate;
        }
    
    public GoodsSpecValueEntity() {
    }

    ;

    public GoodsSpecValueEntity(   Long id ,   Long specId ,   String specValue ,   LocalDateTime gmtCreate ,   LocalDateTime gmtUpdate  ) {
                    this.id= id;
                    this.specId= specId;
                    this.specValue= specValue;
                    this.gmtCreate= gmtCreate;
                    this.gmtUpdate= gmtUpdate;
            }

    ;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
