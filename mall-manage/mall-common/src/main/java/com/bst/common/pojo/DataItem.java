package com.bst.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataItem {

    /// <summary>
    ///
    /// </summary>
    public String time ;
    /// <summary>
    ///
    /// </summary>
    public String ftime ;
    /// <summary>
    /// 客户 签收人: 邮件收发章 已签收 感谢使用圆通速递，期待再次为您服务
    /// </summary>
    public String context ;
    /// <summary>
    ///
    /// </summary>
    public String location ;
}
