package com.bst.goods.model;

/**
 * 状态码
 *
 * @author zouqiang
 * @create 2018-10-30 10:58
 **/
public class CategoryCode {

    /**
     * Insert或Update数据时违反了完整性，例如违反了惟一性限制
     */

    public final static Integer DATAINTEGRITYVIOLATION = 501;

    /**
     *默认 0 为一级分类
     */
    public final static Long THEDEFAULTLEVEL = 0L;
    /**
     *默认 -1 为公共一级分类
     */

    public final static Long THEALLDEFAULTLEVEL = -1L;
}
