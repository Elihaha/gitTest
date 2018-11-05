package com.bst.common.entity.goods;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

    import lombok.Builder;


/**
 * 规格表
 *
 * @author pengzhen
 * @email pengzhen
 * @date 2018-10-26 09:45:33
 */
@Builder
public class GoodsSpecEntity implements Serializable {
    private static final long serialVersionUID = 1L;

         //主键
         private Long id;
            //规格编号
        private String specNo;
            //规格名称
        private String specName;
         //规格类型
        private Byte type;
         //规格类型值
        private Long shopId;
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
         * 设置：规格编号
         */
        public void setSpecNo(String specNo) {
            this.specNo = specNo;
        }

        /**
         * 获取：规格编号
         */
        public String getSpecNo() {
            return specNo;
        }
            /**
         * 设置：规格名称
         */
        public void setSpecName(String specName) {
            this.specName = specName;
        }

        /**
         * 获取：规格名称
         */
        public String getSpecName() {
            return specName;
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }


    public GoodsSpecEntity(){}

    public GoodsSpecEntity(Long id, String specNo, String specName, Byte type, Long shopId, LocalDateTime gmtCreate, LocalDateTime gmtUpdate) {
        this.id = id;
        this.specNo = specNo;
        this.specName = specName;
        this.type = type;
        this.shopId = shopId;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
    }
}
