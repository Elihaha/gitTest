package com.bst.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.bst.backcommon.conditional.DefaultSystemUploadPath;
import com.bst.backcommon.io.excel.ExcelReader;
import com.bst.backcommon.io.excel.ExcelWriter;
import com.bst.common.entity.order.OrderChild;
import com.bst.common.mapper.order.OrderChildMapper;
import com.bst.common.modle.order.OrderLogisticsDto;
import com.bst.common.modle.order.OrderLogisticsVO;
import com.bst.common.pojo.ResultData;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import com.bst.order.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/20 12:09 2018 09
 */
@Service()
@Slf4j
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    DefaultSystemUploadPath defaultSystemUploadPath;

    @Autowired
    OrderChildMapper orderChildMapper;

    @Autowired
    JedisClusterUtils jedisCluster;
    @Autowired
    RedisParam redisParam;

    /**
     * 生成Excel
     *
     * @param logisticsEntities
     * @return
     */
    @Override
    public Optional<String> generateExcelsheet(List<OrderLogisticsVO> logisticsEntities) {
        String filePath;
        /**
         *    读取 模板 xlsx
         */
        try (ExcelWriter writer = new ExcelWriter(getClass().getResourceAsStream("/demo.xlsx"));) {
            writer.deleteSheet(0);
            writer.createSheet("物流信息");
//            开始添加数据
            fileHead(writer);
            for (int i = 0; i < logisticsEntities.size(); i++) {
                OrderLogisticsVO orderLogisticsEntity = logisticsEntities.get(i);
                //  将数据添加到Redis里面
                String key = redisParam.getOrderlogistics() + orderLogisticsEntity.getSignerPhone();
                String orderId = orderLogisticsEntity.getOrderId();
                OrderChild object = orderChildMapper.selectByOrderNo(orderId);
                System.out.println(jedisCluster.hset(
                        //  key    键  由手机号码  组成
                        key,
                        //  felid    订单号组成
                        orderId,
                        //  value   状态
                        JSON.toJSONString(object)));


                fillData(writer, i, orderLogisticsEntity);
//                writer.setTableHeader(0, "测222试");
                setExcelStyle(writer);
                writer.setRowHeight(18);
            }
            filePath = defaultSystemUploadPath.getCurrentSystemUploadPath() + "/" + System.currentTimeMillis() + ".xlsx";
//            FileUtil.mkdirs(filePath);
            writer.save(filePath);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
        return Optional.of(filePath);
    }

    private void fileHead(ExcelWriter writer) {
        writer.saveDataByRow(
                Stream.of(
                        "序号",
                        "订单序号",
                        "商品名称",
                        "商品数量",
                        "收货人",
                        "收货电话",
                        "省份",
                        "城市",
                        "地区",
                        "地址",
                        "快递公司",
                        "快递编号",
                        "发货时间",
                        "快递状态  5.待发货、6发货中、7已收货").collect(Collectors.toList()
                )
        );
    }

    /**
     * 设置宽高 等的
     *
     * @param writer
     */
    private void setExcelStyle(ExcelWriter writer) {
        writer.setColumnWidth(5, 20);
        writer.setColumnWidth(9, 20);
        writer.setColumnWidth(11, 40);
        writer.setColumnWidth(12, 30);
    }

    /**
     * 填充数据   将一个orderLogisticsEntity  转换成一行数据
     *
     * @param writer
     * @param i
     * @param orderLogisticsEntity
     */
    private void fillData(ExcelWriter writer, int i, OrderLogisticsVO orderLogisticsEntity) {
        try {
            writer.saveDataByRow(
                    Stream.of(
                            i,
                            orderLogisticsEntity.getOrderId(),
                            orderLogisticsEntity.getGoodsName(),
                            orderLogisticsEntity.getGoodsCount(),
                            orderLogisticsEntity.getSigner(),
                            orderLogisticsEntity.getSignerPhone(),
                            orderLogisticsEntity.getProvince(),
                            orderLogisticsEntity.getCity(),
                            orderLogisticsEntity.getArea(),
                            orderLogisticsEntity.getAddress(),
                            orderLogisticsEntity.getDhl(),
                            orderLogisticsEntity.getTrackingNumber(),
                            orderLogisticsEntity.getInputTime(),
                            orderLogisticsEntity.getStatus()).collect(Collectors.toList()
                    )
            );
        } catch (Exception e) {
           // e.printStackTrace();
            log.error(e.getMessage(),e);
            writer.saveDataByRow(
                    Stream.of(
                            e.getMessage()
                           ).collect(Collectors.toList()
                    )
            );
        }
    }


    public Optional<List<OrderLogisticsDto>> excelSheetToObject(String dataPath, ResultData resultData) {
        int myI = 0;
        List<OrderLogisticsDto> orderLogisticsDtos = new ArrayList<>();
        try (ExcelReader writer = new ExcelReader(new FileInputStream(new File(dataPath)));) {
            writer.openTable("物流信息");
            int columnCount = writer.getColumnCount();
            for (int i = 1; i < columnCount; i++) {
                myI = i;
                int rowCount = writer.getRowCount(i);
                if (rowCount == 14) {
                    //  获取那一列 所有的数据
                    List<Cell> tableRow = writer.getTableRow(i);
                    //*   判断 订单号
                    String orderCellValue = checkOrder(tableRow);

                    // *   判断 商品名称
                    String goodsNameValue = checkGoodsName(tableRow);
                    // *   判断 数量
                    String goodsCountValue = checkGoodsCount(tableRow);
                    // *   判断 进货人
                    String signer = checkSigner(tableRow);
                    // *   判断 用户电话
                    String signerCellValue = checkSignerPhone(tableRow);
                    // *   判断 省
                    String provinceCellValue = checkProvince(tableRow);
                    // *   判断 市
                    String cityCellValue = checkCity(tableRow);
                    // *   判断 区
                    String areaCellValue = checkArea(tableRow);
                    // *   判断 详细地址
                    String addressCellValue = checkAddress(tableRow);
                    // *   判断 快递公司
                    String dhlCellValue = checkDhl(tableRow);

                    //  *  判断订单号

                    String trackingNumberCellValue = checkTrackingNumber(tableRow);
//                判断时间日期
                    String dateTimeCellValue = checkDateTime(tableRow);
//                    String trackingNumberCellValue = checkTrackingNumber(tableRow);

                    String key = redisParam.getOrderlogistics() + signerCellValue.trim();
                    Optional<String> hget = jedisCluster.hget(
                            //  key  手机号
                            key,
                            //  felid    键  订单号 加
                            orderCellValue
                    );
                    final String obj = hget.get();
                    if (!Objects.isNull(obj)) {
                        OrderChild orderChild = JSON.parseObject(obj, OrderChild.class);
                        OrderLogisticsDto orderLogisticsDto = new OrderLogisticsDto();
                        if (orderChild.getStatus() == 5) {
                            orderLogisticsDto.setOrderId(orderChild.getOrderNo());
                            orderLogisticsDto.setStatus(6);
                            orderLogisticsDto.setTrackingNumber(trackingNumberCellValue);
                            orderLogisticsDto.setDhl(dhlCellValue);
                            orderLogisticsDtos.add(orderLogisticsDto);
                        }
                    } else {
                        throw new RuntimeException(" 未找到相關數據   请检查相关 手机号  订单号是否正确  請核實信息再試 ");
                    }
                } else {
                    throw new RuntimeException("列出现数据丢失");
                }
            }
            return Optional.of(orderLogisticsDtos);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            resultData.setMessage(myI + 1 + " 行" + e.getMessage());
            return Optional.empty();
        }
//        return Optional.empty();
    }

    private String checkOrder(List<Cell> tableRow) {
        Cell order = tableRow.get(1);
        String signerCellValue = order.getStringCellValue();
        if (Objects.isNull(signerCellValue)) {
            throw new RuntimeException("订单号出错 为空");
        }
        return signerCellValue;
    }

    private String check(List<Cell> tableRow, int i2, String message) {
        Cell signer = tableRow.get(i2);
        String signerCellValue = signer.getStringCellValue();
        long l = -1;
        try {
            l = Long.parseLong(signerCellValue);
        } catch (NumberFormatException e) {
            throw new RuntimeException(message);
        }
        return signerCellValue;
    }

    private String checkString(List<Cell> tableRow, int i2, String message) {
        Cell signer = tableRow.get(i2);
        String signerCellValue = signer.getStringCellValue();
        if (Objects.isNull(signerCellValue)) {
            throw new RuntimeException(message);
        }
        return signerCellValue;
    }


    private String checkGoodsName(List<Cell> tableRow) {
        String signerCellValue = checkString(tableRow, 2, "商品名称");
        return signerCellValue;
    }

    private String checkGoodsCount(List<Cell> tableRow) {
        String signerCellValue = check(tableRow, 3, "商品数量");
        return signerCellValue;

    }

    private String checkSigner(List<Cell> tableRow) {
        String signerCellValue = checkString(tableRow, 4, "进货人出错");
        return signerCellValue;
    }

    private String checkSignerPhone(List<Cell> tableRow) {
        String signerCellValue = check(tableRow, 5, "手机号出错");
        return signerCellValue;
    }


    private String checkProvince(List<Cell> tableRow) {
        String signerCellValue = checkString(tableRow, 6, "省");
        return signerCellValue;
    }

    private String checkCity(List<Cell> tableRow) {
        String signerCellValue = checkString(tableRow, 7, "市");
        return signerCellValue;

    }

    private String checkArea(List<Cell> tableRow) {
        String signerCellValue = checkString(tableRow, 8, "区");

        return signerCellValue;
    }

    private String checkAddress(List<Cell> tableRow) {
        String signerCellValue = checkString(tableRow, 9, "地址");
        return signerCellValue;
    }

    private String checkDhl(List<Cell> tableRow) {
        String signerCellValue = checkString(tableRow, 10, "快遞公司");
        return signerCellValue;
    }


    private String checkTrackingNumber(List<Cell> tableRow) {
        String trackingNumberCellValue = checkString(tableRow, 11, "物流单号出错");
        return trackingNumberCellValue;
    }

    private String checkDateTime(List<Cell> tableRow) {
        String trackingNumberCellValue = checkString(tableRow, 12, "时间出现问题");
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime.parse(trackingNumberCellValue, df);
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException("时间转换出错");
        }
        return trackingNumberCellValue;
    }
}
