package com.bst.common.modle.goods;

import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.entity.goods.GoodsSpu;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

/**
 * @author lumin
 * @description:
 * @create 2018-09-18 9:56
 **/
public class GoodsQuery extends GoodsSpu {
    private Integer PageSizeKey;

    private Integer PageNumKey;
    /**
     * 商品状态
      */
    private Byte statusRange;
   /**
   *"开始时间"
   *
    */
    private LocalDate startUpdate;
   /**
    * "结束时间"
    * */
    private LocalDate endUpdate;

    /**
     * 是否查询商品售罄
     */

    private Boolean soldOut;

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

    public Byte getStatusRange() {
        return statusRange;
    }

    public void setStatusRange(Byte statusRange) {
        this.statusRange = statusRange;
    }

    public LocalDate getStartUpdate() {
        return startUpdate;
    }

    public void setStartUpdate(LocalDate startUpdate) {
        this.startUpdate = startUpdate;
    }

    public LocalDate getEndUpdate() {
        return endUpdate;
    }

    public void setEndUpdate(LocalDate endUpdate) {
        this.endUpdate = endUpdate;
    }

    public Boolean getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(Boolean soldOut) {
        this.soldOut = soldOut;
    }
}
