package com.bst.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryKuaidiJson {

    /// <summary>
    ///
    /// </summary>
    public String message;/// <summary>
    ///
    /// </summary>
    public String nu ;// <summary>
    ///
    /// </summary>
    public String ischeck;/// <summary>
    ///
    /// </summary>
    public String condition;
    /// <summary>
    ///
    /// </summary>
    public String com ;// <summary>
    ///
    /// </summary>
    public String status ;// <summary>
    ///
    /// </summary>
    public String state ;// <summary>
    ///
    /// </summary>
    public List<DataItem> data ;
}
