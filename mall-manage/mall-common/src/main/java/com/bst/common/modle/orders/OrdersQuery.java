package com.bst.common.modle.orders;

import com.bst.common.entity.order.Orders;


import java.util.Date;

/**
 * @author zouqiang
 * @create 2018-09-25 10:41
 **/
public class OrdersQuery  extends Orders {
    private Integer PageSizeKey;
    private Integer PageNumKey;

    private Date startUpdate;

    private Date endUpdate;


    public Integer getPageSizeKey() {
        return PageSizeKey;
    }

    public void setPageSizeKey(Integer pageSizeKey) {
        PageSizeKey = pageSizeKey;
    }

    public Integer getPageNumKey() {
        return PageNumKey;
    }

    public void setPageNumKey(Integer pageNumKey) {
        PageNumKey = pageNumKey;
    }

    public Date getstartUpdate() {
        return startUpdate;
    }

    public void setstartUpdate(Date startUpdate) {
        this.startUpdate = startUpdate;
    }

    public Date getendUpdate() {
        return endUpdate;
    }

    public void setendUpdate(Date endUpdate) {
        this.endUpdate = endUpdate;
    }
}
