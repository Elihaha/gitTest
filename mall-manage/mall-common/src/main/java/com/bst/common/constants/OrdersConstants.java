package com.bst.common.constants;

public enum  OrdersConstants {


 //   -1.删除、 0.草稿、、，3取消支付、4.取消订单、5.待发货、6发货中、7已收货、8退货中、9.已退货、10.已退款。
     DELETE_ORDER(-1,"删除"),
    //  待支付  1.待支付
    TO_BE_PAID(1,"待支付"),

    PAID(2,"已支付"),

    //  取消支付
    CANCEL_PAYMENT(3,"取消支付"),

    //  取消订单
    CANCEL_ORDER(4,"取消订单"),

    //  待发货
    TO_BE_DELIVERED(5,"待发货"),
    //  待收货
    PENDING_RECEIPT(6,"待收货"),
    //  已收货
    RECEIVED(7,"已收货"),
    //  退货
    RETURN_OF_GOODS(8,"退货"),

    //  可以退货
    CAN_RETURN(1,"可以退货"),
    //  开启退货
    OPEN_RETURN(7,"开启退货"),
    //  关闭退货
    CLOSE_THE_RETURN(8,"关闭退货"),

    ;

    private String name;
    private Byte statusNo;

    OrdersConstants( int statusNo,String name) {
        this.name = name;
        this.statusNo = (byte) statusNo;
    }

    public static String getValue(byte number){
        final OrdersConstants[] values = OrdersConstants.values();
        for (OrdersConstants value : values) {
            if(value.ordinal()==number){
                return  value.getName();
            }
        }
        throw  new RuntimeException("  脏数据");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStatusNo() {
        return statusNo;
    }

    public void setStatusNo(Byte statusNo) {
        this.statusNo = statusNo;
    }



}




