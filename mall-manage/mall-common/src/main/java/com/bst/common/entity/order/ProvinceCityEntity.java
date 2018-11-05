package com.bst.common.entity.order;

import lombok.Builder;

import java.io.Serializable;


/**
 * 城市配置表
 *
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-17 19:27:22
 */
@Builder
public class ProvinceCityEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //
        private Integer id;
            //
        private String name;
            //
        private Integer grade;
            //
        private Integer province;
            //
        private Integer enable;
    
            /**
         * 设置：
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * 获取：
         */
        public Integer getId() {
            return id;
        }
            /**
         * 设置：
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 获取：
         */
        public String getName() {
            return name;
        }
            /**
         * 设置：
         */
        public void setGrade(Integer grade) {
            this.grade = grade;
        }

        /**
         * 获取：
         */
        public Integer getGrade() {
            return grade;
        }
            /**
         * 设置：
         */
        public void setProvince(Integer province) {
            this.province = province;
        }

        /**
         * 获取：
         */
        public Integer getProvince() {
            return province;
        }
            /**
         * 设置：
         */
        public void setEnable(Integer enable) {
            this.enable = enable;
        }

        /**
         * 获取：
         */
        public Integer getEnable() {
            return enable;
        }
    
    public ProvinceCityEntity() {
    }

    ;

    public ProvinceCityEntity(Integer id , String name , Integer grade , Integer province , Integer enable  ) {
                    this.id= id;
                    this.name= name;
                    this.grade= grade;
                    this.province= province;
                    this.enable= enable;
            }

    ;

}
