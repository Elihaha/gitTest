package com.bst.mallh5.model.goods;

/**
 * 状态码
 *
 * @author zouqiang
 * @create 2018-10-30 10:58
 **/
public class CategoryCode {

    /**
     *默认 0 为一级分类
     */
    public final static Long THEDEFAULTLEVEL = 0L;
    /**
     *默认 -1 为公共一级分类
     */

    public final static Long THEALLDEFAULTLEVEL = -1L;

    //图片类型 1 为商品展示图
    public final static Byte IMAGETYPE = 1;
    // 1 商品状态为上架
    public final static Byte GOODSSTATUS = 1;
}
