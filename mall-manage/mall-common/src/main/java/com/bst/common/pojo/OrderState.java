package com.bst.common.pojo;

/*
  * 订单状态
 * @author zouqiang
 * @date 2018/9/20 12:40
 * @param
 * @return
 */

public enum OrderState {
    UNPAID("待支付", 1), UNDELIVERY("待发货", 2), DELIVERY("已发货", 3), COMPLETE("交易完成", 4),CHARGEBACK("已退单", 5);
        // 成员变量
        private String name;
        private int index;
        // 构造方法
        private OrderState(String name, int index) {
            this.name = name;
            this.index = index;
        }
        // 普通方法
        public static String getName(int index) {
            for (OrderState c : OrderState.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }
        // get set 方法
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }

}
