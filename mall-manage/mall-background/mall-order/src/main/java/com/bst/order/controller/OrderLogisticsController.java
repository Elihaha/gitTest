package com.bst.order.controller;

import com.bst.backcommon.conditional.DefaultSystemUploadPath;
import com.bst.common.config.Snowflake.SnowflakeId;
import com.bst.common.constants.HttpConstants;
import com.bst.common.entity.order.OrderChild;
import com.bst.common.entity.order.OrderLogisticsEntity;
import com.bst.common.entity.order.Orders;
import com.bst.common.mapper.order.OrderChildMapper;
import com.bst.common.mapper.order.OrderLogisticsDao;
import com.bst.common.modle.Result;
import com.bst.common.modle.order.*;
import com.bst.common.pojo.*;
import com.bst.common.utils.KuaiDI100Util;
import com.bst.order.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 邮费
 * 订单物流表
 *
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-17 19:27:22
 */
@RestController
@Slf4j
@Api("订单物流")
@RequestMapping("orderlogistics")
public class OrderLogisticsController {

    @Autowired
    private CourierCompanyService courierCompanyService;


    @Autowired
    private OrderLogisticsService orderLogisticsService;

    @Autowired
    private OrderLogisticsService orderLogisticsDao;

    @Autowired
    DefaultSystemUploadPath defaultSystemUploadPath;

    @Autowired
    private OrdersManageService insertSelective;
    @Autowired
    private OrderChildService orderChildService;

    /**
     * 列表
     */
    @ApiOperation(value = "订单物流列表  可加 搜索", response = Result.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/list")
    public Result list(@ApiParam Pagination pagination, @ApiParam OrderLogisticscQueryConditions params) {
        try {
            //查询列表数据
//        Query query = new Query(params);
            if (pagination.getPage() < 0) {
                return Result.error(" 请输入正确的页码");
            }
            if (pagination.getNumber() < 0) {
                return Result.error(" 请输入正确的页数");
            }
            Integer status = params.getStatus();
            if (!Stream.of(6, 7, 20, status).anyMatch(s -> s.equals(status))) {
                return Result.error(" 狀態出錯， 請輸入狀態  或者正确的状态");
            }

            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
//            状态
            stringObjectHashMap.put("status", status == 20 ? Stream.of(6, 7).collect(Collectors.toList()) : Stream.of(status).collect(Collectors.toList()));
            //  选填部分
//         订单号   order_id
            stringObjectHashMap.put("orderId", params.getOrderId());
//          收货人  signer
            stringObjectHashMap.put("signer", params.getSigner());
//          手机号  signer_phone
            stringObjectHashMap.put("signerPhone", params.getSignerPhone());
//          发货时间      开始时间  - 结束时间
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                String startDateTime = params.getStartDateTime();
                if (StringUtils.isNotBlank(startDateTime)) {
                    stringObjectHashMap.put("startDateTime", LocalDateTime.of(LocalDate.parse(startDateTime, df), LocalTime.MIN));
                }
                String endDateTime = params.getEndDateTime();
                if (StringUtils.isNotBlank(startDateTime)) {
                    stringObjectHashMap.put("endDateTime", LocalDateTime.of(LocalDate.parse(endDateTime, df), LocalTime.MAX));
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException("时间转换异常");
//                e.printStackTrace();
            }
//          请输入物流单号
            stringObjectHashMap.put("trackingNumber", params.getTrackingNumber());
            List<OrderLogisticsVO> orderLogisticsList = orderLogisticsService.queryList(pagination, stringObjectHashMap);
            int total = orderLogisticsService.queryTotal(pagination, stringObjectHashMap);
            return Result.ok(new HashMap<String, Object>() {{
                put("data", orderLogisticsList);
                put("sum", total);
            }});

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }


    /**
     * 信息
     */
    @ApiOperation(value = " 信息 ", response = Result.class)
    @ApiImplicitParams(
            value = {@ApiImplicitParam(name = "id", value = "492424621950238720", defaultValue = "492424621950238720", dataTypeClass = Long.class, paramType = "path")}
    )
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        try {
            OrderLogisticsEntity orderLogistics = orderLogisticsService.queryObject(id);

            return Result.ok(new HashMap<String, Object>() {{
                put("orderLogistics", orderLogistics);
            }});
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }


    /**
     * 保存
     */
    @ApiOperation(value = "保存    ", response = Result.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/save")
    public Result save(@ApiParam @RequestBody OrderLogisticsInsertPojo orderLogistics) {
        try {
            orderLogisticsService.save(orderLogistics);

            return Result.ok();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }


    /**
     * 创建子订单
     */
    @ApiOperation(value = "创建子订单    ", response = Result.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/orderChild")
    public Result orderChild(@RequestBody @ApiParam OrderChildGenerate orderLogistics) {
        try {

            insertSelective.createOrderChild(orderLogistics);

            return Result.ok();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }


    /**
     * 发货
     */
    @ApiOperation(value = "发货    ", response = Result.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/ship")
    public Result ship(@RequestBody @ApiParam OrderLogisticsUpdateDto orderLogistics) {
        try {
            orderLogisticsService.updateByOrderNo(orderLogistics);
            orderLogisticsService.update(orderLogistics);
            return Result.ok();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        try {
            orderLogisticsService.deleteBatch(ids);
            return Result.ok();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }


//    @PostMapping("/ship/{ids}")
//    public Result ship(@RequestParam(value = "ids") String ids) {
//        try {
//
//            return Result.ok();
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return Result.error(e.getMessage());
//        }
//    }

    /**
     * 快递公司
     */
    @ApiOperation(value = "快递公司    ", response = Result.class)
    @GetMapping("/courierCompany/")
    public Result courierCompany() {
        try {

//            List<ExpressType> collect = Stream.of(ExpressType.values()).collect(Collectors.toList());
            List<ExpressTypes> collect = courierCompanyService.queryList(new HashMap<>()).stream().map(courierCompany1 -> ExpressTypes.builder().code(courierCompany1.getCode()).name(courierCompany1.getName()).build()).collect(Collectors.toList());
            return Result.ok(collect);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }


    /**
     * http://localhost:9097/orderlogistics/download?page=1&number=3
     * excel  文件上传
     */
    @ApiOperation(value = "  上传 excel     ", response = Result.class)
    //上传路径
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {

        File localFile = null;
        ResultData resultData = null;
        try {
            System.out.println(file.getName());
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);
            System.out.println(file.getSize());
            if (!originalFilename.endsWith(".xlsx")) {
                return Result.error("请上传过一个 excel 表格");
            }
            //创建本地文件
            localFile = new File(defaultSystemUploadPath.getCurrentSystemUploadPath(), System.currentTimeMillis() + ".xlsx");
            Runtime.getRuntime().exec("chmod 777 " + defaultSystemUploadPath.getCurrentSystemUploadPath());
            localFile.setWritable(true, false);
            //把传上来的文件写到本地文件
            file.transferTo(localFile);
            //返回localFile文件路径
            resultData = new ResultData();
            Optional<List<OrderLogisticsDto>> orderLogisticsDtos = excelService.excelSheetToObject(localFile.getAbsolutePath(), resultData);

            orderLogisticsService.updateByPrimaryKey(orderLogisticsDtos);
        } catch (IOException | IllegalStateException e) {
//            e.printStackTrace();
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
        return Result.ok(localFile.getAbsolutePath() + "   " + resultData.getMessage());
    }


    @Autowired
    ExcelService excelService;


    @ApiOperation(value = "  下载excel     ", response = Result.class)
    @GetMapping("/download")
    public void download(@ApiParam Pagination pagination, @ApiParam OrderLogisticscQueryConditions params, HttpServletRequest request, HttpServletResponse response) {
        try (OutputStream outputStream = response.getOutputStream();) {
            /**
             *   从数据库获取最新的数据
             */
            Result list = list(pagination, params);
            HashMap<String, Object> data = (HashMap<String, Object>) list.getData();
            Object data1 = data.get("data");
            if (data1 == null) {
                ((ServletOutputStream) outputStream).print("下载数据为空");
                return;
            }
            List<OrderLogisticsVO> orderLogisticsEntities = (List<OrderLogisticsVO>) data1;
            Optional<String> s = excelService.generateExcelsheet(orderLogisticsEntities);
            /**
             *   设置下载
             */
            s.ifPresent(s1 -> {
                try (InputStream inputStream = new FileInputStream(new File(s1));) {
                    //设置内容类型为下载类型
                    response.setContentType("application/x-download");
                    //设置请求头 和 文件下载名称
                    response.addHeader("Content-Disposition", "attachment;filename=logistics.xlsx");
                    //用 common-io 工具 将输入流拷贝到输出流
                    IOUtils.copy(inputStream, outputStream);
                } catch (Exception e) {
                    try {
                        ((ServletOutputStream) outputStream).print(e.getMessage());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
//                    e.printStackTrace();
                }
            });
//            if(!s.isPresent()){
//                ((ServletOutputStream) outputStream).print("系统错误");
//            }
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @ApiOperation("单个订单物流查询 ")
    @ApiImplicitParam(name = "orderNo", value = "orderNo", required = false, dataType = "string", paramType = "path", defaultValue = "1000001709357141")
    @GetMapping("queryInfo/{orderNo}")
    public Result queryLogisticsInfo(@PathVariable(value = "orderNo") String orderNo) {
        try {
            if (StringUtils.isBlank(orderNo)) {
                return Result.error(" 参数需要大于0");
            }
            //   根据子订单id  获取  物流编号
            OrderLogisticsEntity orderLogisticsEntity = orderLogisticsDao.getOrderLogisticsEntitiesByOrderNO(orderNo);
            String trackingNumber = orderLogisticsEntity.getTrackingNumber();
//            String trackingNumber = null;
//            trackingNumber = 889999834560284183L+"";
            // 根据  物流编号 获取相关信息
            Optional<QueryKuaidiJson> queryKuaidiJson = KuaiDI100Util.kuaiDiQuery(trackingNumber);
            if (!queryKuaidiJson.isPresent()) {
                return Result.ok(HttpConstants.SUCCESS, "没有任何数据");
            }
            return Result.ok(queryKuaidiJson.get());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(HttpConstants.UNKNOW + "");
        }
    }


}
