package com.bst.order.service;

import com.bst.common.modle.order.OrderLogisticsDto;
import com.bst.common.modle.order.OrderLogisticsVO;
import com.bst.common.pojo.ResultData;

import java.util.List;
import java.util.Optional;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/20 12:09 2018 09
 */
public interface ExcelService {

      public Optional<String> generateExcelsheet(List<OrderLogisticsVO> logisticsEntities);

      public Optional<List<OrderLogisticsDto>> excelSheetToObject(String dataPath, ResultData resultData);
}
