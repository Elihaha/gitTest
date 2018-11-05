package com.bst.mallh5.model.goods;

/**
 * @author zouqiang
 * @create 2018-11-01 16:35
 **/
public class GoodsCategoryResquest {

    private Long id;

    private Long shopId;

    private Integer page;

    private Integer rows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
