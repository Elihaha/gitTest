package com.bst.common.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

    import lombok.Builder;


/**
 * 运费配置
 *
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-18 16:10:33
 */
@Builder
public class OrderPostageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //
        private Long id;
            //
        private Long shopId;
            //
        private String province;
            //
        private String city;
            //
        private BigDecimal postage;
            //
        private Integer status;
    
            /**
         * 设置：
         */
        public void setId(Long id) {
            this.id = id;
        }

        /**
         * 获取：
         */
        public Long getId() {
            return id;
        }
            /**
         * 设置：
         */
        public void setShopId(Long shopId) {
            this.shopId = shopId;
        }

        /**
         * 获取：
         */
        public Long getShopId() {
            return shopId;
        }
            /**
         * 设置：
         */
        public void setProvince(String province) {
            this.province = province;
        }

        /**
         * 获取：
         */
        public String getProvince() {
            return province;
        }
            /**
         * 设置：
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         * 获取：
         */
        public String getCity() {
            return city;
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
        public void setStatus(Integer status) {
            this.status = status;
        }

        /**
         * 获取：
         */
        public Integer getStatus() {
            return status;
        }
    
    public OrderPostageEntity() {
    }

    ;

    public OrderPostageEntity(   Long id ,   Long shopId ,   String province ,   String city ,   BigDecimal postage ,   Integer status  ) {
                    this.id= id;
                    this.shopId= shopId;
                    this.province= province;
                    this.city= city;
                    this.postage= postage;
                    this.status= status;
            }

    ;

}
