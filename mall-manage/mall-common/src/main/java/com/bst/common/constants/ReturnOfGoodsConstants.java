package com.bst.common.constants;

public enum ReturnOfGoodsConstants {

    // 提交申请
    // 待卖家处理
    SUBMIT_APPLICATION("提交申请"),
    // 卖家同意
    // 系统退款中
    SYSTEM_REFUND("系统退款中"),
    //  等待支付接口返回
    // 退款成功
    REFUND_SUCCESSFULLY("退款成功"),
    // 卖家拒绝
    // 待买家处理
    SELLER_REFUSED("卖家拒绝"),
    //  用户取消退款
    // 退款关闭
    USER_CANCELS_REFUND("用户取消退款"),
    ;

    private String value;

    ReturnOfGoodsConstants(String value) {
        this.value = value;
    }

    public static String getValue(byte number){
        final ReturnOfGoodsConstants[] values = ReturnOfGoodsConstants.values();
        for (ReturnOfGoodsConstants value : values) {
             if(value.ordinal()==number){
                 return  value.getValue();
             }
        }
        throw  new RuntimeException("  脏数据");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}




