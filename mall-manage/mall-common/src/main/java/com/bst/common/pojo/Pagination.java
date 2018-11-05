package com.bst.common.pojo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/25 9:07 2018 09
 */

@ApiModel
public class Pagination {
    @ApiModelProperty(name = "page", value = "第几页", example = "1", dataType = "int",required = true)
    @NotNull(message = "页数不能为null")
    private Integer page;

    @ApiModelProperty(name = "number", value = "每页多少条", example = "10", dataType = "int",required = true)
    @NotNull(message = "每页多少条数不能为null")
    private Integer number;

    public Pagination(Integer page, Integer number) {
        this.page = page;
        this.number = number;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
