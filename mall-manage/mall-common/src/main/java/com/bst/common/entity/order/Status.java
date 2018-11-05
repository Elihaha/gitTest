package com.bst.common.entity.order;

public class Status {
    //删除
    public static final byte delete=-1;
    //失效
    public static final byte invaild=4;
    //待发货
    public static final byte waitinDelivery=5;
    //发货中
    public static final byte shipmenting=6;
    //已收货
    public static final byte collectGoods=7;
    //申請退貨
    public static final byte applySaleReturn=8;
    //退货中
    public static final byte SaleReturning=9;
    //已退货
    public static final byte SaleReturned=10;
}
