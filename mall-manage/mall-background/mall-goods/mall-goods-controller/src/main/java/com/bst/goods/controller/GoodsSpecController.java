package com.bst.goods.controller;

import java.util.List;
import java.util.Map;

import com.bst.backcommon.permission.PermissionInfoUtil;
import com.bst.common.entity.goods.GoodsSpecEntity;
import com.bst.common.modle.Result;
import com.bst.goods.service.GoodsSpecService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;



/**
 * 规格表
 * 
 * @author pengzhen
 * @email pengzhen
 * @date 2018-10-26 09:45:33
 */
@RestController
@Slf4j
@RequestMapping("goodsSpec")
public class GoodsSpecController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsSpecController.class);

	@Autowired
	private GoodsSpecService goodsSpecService;


	/**
	 *
	 * @param params 商铺 id 以及分頁
	 * @return 返回可用的商铺规格以及公用規格
	 */

	@PostMapping("/list")
	@ApiOperation(value = "查询所有可用的规格",notes = "所有店铺规格和通用规格")
	public Result list(@RequestBody Map<String, Object> params){
        try {
        	if (params == null) {
        		return Result.error("查询规格失败：入参不完整");
        	}

			long shopId  = PermissionInfoUtil.getCurrentLogginUser().getShopInfo().getId();
			params.put("shopId",shopId);

  			Result result = goodsSpecService.queryList(params);
        	return  result;
        } catch (Exception e) {
			LOGGER.error(">>>>>>>>>>查询规格，异常：",e);
            return Result.error(e.getMessage());
        }
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
	public Result info(@PathVariable("id") Long id){
        try {
		GoodsSpecEntity goodsSpec = goodsSpecService.queryObject(id);
		return Result.ok("goodsSpec", goodsSpec);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Result.error(e.getMessage());
        }
	}

	/**
	 *
	 * @param goodsSpec 规格对象
	 * @return 返回创建结果
	 */
	@PostMapping("/save")
	@ApiOperation(value = "自定义规格",notes = "需要自定义规格名字,specName不能为空")
	public Result saveGoodsEntity(@RequestBody GoodsSpecEntity goodsSpec){
        try {
        	if (StringUtils.isBlank(goodsSpec.getSpecName())) {
				return Result.error("自定义规格失败：入参不完整");
			}
			long shopId  = PermissionInfoUtil.getCurrentLogginUser().getShopInfo().getId();
        	goodsSpec.setShopId(shopId);
			goodsSpecService.save(goodsSpec);
			return Result.ok("自定义规格成功",goodsSpec.getId());
     }catch (Exception e) {
        log.error(">>>>>>>>>>自定义规格失败,异常",e);
        return Result.error("自定义规格失败");
    	}
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	public Result update(@RequestBody GoodsSpecEntity goodsSpec){
        try {
        	goodsSpecService.update(goodsSpec);
		return Result.ok();
    } catch (Exception e) {
        log.error(e.getMessage(),e);
        return Result.error(e.getMessage());
        }
	}


	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public Result delete(@RequestBody Long[] ids){
        try {
		goodsSpecService.deleteBatch(ids);
		return Result.ok();
        } catch (Exception e) {
        log.error(e.getMessage(),e);
        return Result.error(e.getMessage());
        }
	}
	
}
