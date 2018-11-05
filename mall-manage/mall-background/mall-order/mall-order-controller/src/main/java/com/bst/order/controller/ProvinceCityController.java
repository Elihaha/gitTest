package com.bst.order.controller;

import com.bst.common.entity.order.ProvinceCityEntity;
import com.bst.common.modle.Result;
import com.bst.order.service.ProvinceCityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 城市配置表
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-09-17 19:27:22
 */
@RestController
@Slf4j
@RequestMapping("provincecity")
public class  ProvinceCityController {
	@Autowired
	private ProvinceCityService provinceCityService;
	
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
		List<ProvinceCityEntity> provinceCityList = provinceCityService.queryList(params);
		int total = provinceCityService.queryTotal(params);
		
//		PageUtils pageUtil = new PageUtils(provinceCityList, total, query.getLimit(), query.getPage());
		
		return Result.ok(new HashMap<String,Object>(){{
			put("total", total);
			put("data", provinceCityList);
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
	public Result info(@PathVariable("id") Integer id){
        try {
		ProvinceCityEntity provinceCity = provinceCityService.queryObject(id);
		
		return Result.ok(new HashMap<String,Object>(){{
		  put("provinceCity", provinceCity);
		}});
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Result.error("内部错误");
        }
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	public Result save(@RequestBody ProvinceCityEntity provinceCity){
        try {
		provinceCityService.save(provinceCity);
		
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
	public Result update(@RequestBody ProvinceCityEntity provinceCity){
        try {
		provinceCityService.update(provinceCity);
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
	public Result delete(@RequestBody Integer[] ids){
        try {
		provinceCityService.deleteBatch(ids);
		
		return Result.ok();
        } catch (Exception e) {
        log.error(e.getMessage(),e);
        return Result.error("内部错误");
        }
	}
	
}
