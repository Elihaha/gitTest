package com.bst.order.controller;

import com.bst.common.modle.Result;
import com.bst.common.pojo.Pagination;
import com.bst.common.modle.order.PostageConfigPojo;
import com.bst.common.modle.order.PostageConfigInsertPojo;
import com.bst.order.service.PostageConfigService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-18 16:10:33
 */
@RestController
@Slf4j
@Api("运费配置")
@RequestMapping("postageconfig")
public class PostageConfigController {
    @Autowired
    private PostageConfigService postageConfigService;

    /**
     * 列表
     */
    @ApiOperation(value = "运费配置列表    可加 搜索 ", response = Result.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ApiImplicitParams(

            {@ApiImplicitParam(name = "like", value = "搜素 ", defaultValue = "", dataTypeClass = String.class, paramType = "query")}
    )
    @GetMapping("/list")
    public Result list(@ApiParam Pagination pagination, @RequestParam(name = "like", required = false) String like) {
        try {
            //查询列表数据
//        Query query = new Query(params);
            if (pagination.getPage() < 0) {
                return Result.error(" 请输入正确的页码");
            }
            if (pagination.getNumber() < 0) {
                return Result.error(" 请输入正确的页数");
            }
            if (org.apache.commons.lang3.StringUtils.isBlank(like)) {
                log.debug("like   is   null");
            }

            return postageConfigService.queryList(pagination, like);


//		PageUtils pageUtil = new PageUtils(postageConfigList, total, query.getLimit(), query.getPage());

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
            value = {@ApiImplicitParam(name = "id", value = "1", defaultValue = "1", paramType = "path")}
    )
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id) {
        try {
            if (id < 1) {
                return Result.error("id 小于  1");
            }
            return postageConfigService.queryObject(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }


    /**
     * 信息
     */
    @ApiOperation(value = "运费信息", response = Result.class)
    @GetMapping("/query/{name}")
    @ApiImplicitParams(
            value = {@ApiImplicitParam(name = "name", value = "1", defaultValue = "1", dataTypeClass = String.class)}
    )
    public Result query(@PathVariable("name") String provinceName) {
        try {
            return Result.ok(postageConfigService.queryByProvince(provinceName));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增    ", response = Result.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/save")
    public Result save(@ApiParam @RequestBody PostageConfigInsertPojo postageConfig) {
        try {
            if (postageConfig.getPostage() == null || StringUtils.isEmpty(postageConfig.getProvince())) {
                return Result.error("邮资   城市错误  ");
            }
            postageConfig.setStatus(0);
            return     postageConfigService.save(postageConfig);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if(e  instanceof MySQLIntegrityConstraintViolationException){
                return  Result.error("名字重复请换个名字试试");
            }
            return Result.error(e.getMessage());
        }
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改    ", response = Result.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/update")
    public Result update(@RequestBody @ApiParam PostageConfigPojo postageConfig) {
        try {
            if (postageConfig.getPostage() == null || StringUtils.isEmpty(postageConfig.getProvince())) {
                return Result.error("邮资   城市错误  ");
            }
            return postageConfigService.update(postageConfig);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除    ", response = Result.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/delete")
    public Result delete(@RequestBody @ApiParam(name = "id 数组") Long[] ids) {
        try {
            for (Long id : ids) {
                if (id < 1) {
                    return Result.error("id  错误  ");
                }
            }
            postageConfigService.deleteBatch(ids);
            return Result.ok();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

}
