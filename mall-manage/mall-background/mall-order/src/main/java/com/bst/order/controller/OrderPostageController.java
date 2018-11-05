package com.bst.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bst.common.entity.order.OrderPostageEntity;
import com.bst.common.modle.Result;
import com.bst.order.service.OrderPostageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;



/**
 * 运费配置
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-18 16:10:33
 */
@RestController
@Slf4j
@RequestMapping("orderpostage")
public class OrderPostageController {
	@Autowired
	private OrderPostageService orderPostageService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	public Result list(@RequestParam Map<String, Object> params){
        try {
		//查询列表数据
//        Query query = new Query(params);
			if(params.getOrDefault("page",-1).equals(-1)){
				return  Result.error(" 请输入正确的页码");
			}
			if(params.getOrDefault("number",-1).equals(-1)){
				return  Result.error(" 请输入正确的页数");
			}
			if(params.getOrDefault("like","asdasdasdasd").equals("asdasdasdasd")){
				params.put("like",null);
			}
		List<OrderPostageEntity> orderPostageList = orderPostageService.queryList(params);
		int total = orderPostageService.queryTotal(params);
		
//		PageUtils pageUtil = new PageUtils(orderPostageList, total, query.getLimit(), query.getPage());
		
		return Result.ok(new HashMap<String,Object>(){{
			put("total",total);
			put("data",orderPostageList);
		}});

        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Result.error("内部错误");
        }
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
	public Result info(@PathVariable("id") Long id){
        try {
		OrderPostageEntity orderPostage = orderPostageService.queryObject(id);
		
		return Result.ok();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Result.error("内部错误");
        }
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	public Result save(@RequestBody OrderPostageEntity orderPostage){
        try {
		orderPostageService.save(orderPostage);
		
		return Result.ok();
    } catch (Exception e) {
        log.error(e.getMessage(),e);
        return Result.error("内部错误");
    }
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	public Result update(@RequestBody OrderPostageEntity orderPostage){
        try {
		orderPostageService.update(orderPostage);
		
		return Result.ok();
    } catch (Exception e) {
        log.error(e.getMessage(),e);
        return Result.error("内部错误");
        }
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public Result delete(@RequestBody Long[] ids){
        try {
		orderPostageService.deleteBatch(ids);
		
		return Result.ok();
        } catch (Exception e) {
        log.error(e.getMessage(),e);
        return Result.error("内部错误");
        }
	}
	
}
