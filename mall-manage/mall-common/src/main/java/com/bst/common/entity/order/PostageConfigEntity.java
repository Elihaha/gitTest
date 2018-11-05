package com.bst.common.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

    import lombok.Builder;


/**
 * 
 *
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-18 16:10:33
 */
@Builder
public class PostageConfigEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //id
        private String id;
            //省份 运费计算是基于省到省的
        private String province;
            //
        private BigDecimal postage;
            //
        private LocalDateTime createTime;
            //
        private LocalDateTime lastUpdateTime;
            //最后修改的人
        private Long lastUpdateUserId;
            //
        private Integer status;

        private  Long shopId;
    
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
        public void setCreateTime(LocalDateTime createTime) {
            this.createTime = createTime;
        }

        /**
         * 获取：
         */
        public LocalDateTime getCreateTime() {
            return createTime;
        }
            /**
         * 设置：
         */
        public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        /**
         * 获取：
         */
        public LocalDateTime getLastUpdateTime() {
            return lastUpdateTime;
        }
            /**
         * 设置：最后修改的人
         */
        public void setLastUpdateUserId(Long lastUpdateUserId) {
            this.lastUpdateUserId = lastUpdateUserId;
        }

        /**
         * 获取：最后修改的人
         */
        public Long getLastUpdateUserId() {
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
    
    public PostageConfigEntity() {
    }

    ;

    public PostageConfigEntity(String id, String province, BigDecimal postage, LocalDateTime createTime, LocalDateTime lastUpdateTime, Long lastUpdateUserId, Integer status, Long shopId) {
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
