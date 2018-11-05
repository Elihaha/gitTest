package com.bst.goods.service.impl;

import com.bst.common.config.Snowflake.IdGenerate;
import com.bst.common.constants.HttpConstants;
import com.bst.common.entity.goods.GoodsSpecEntity;
import com.bst.common.entity.goods.GoodsSpecValueEntity;
import com.bst.common.mapper.goods.GoodsSpecDao;
import com.bst.common.modle.Result;
import com.bst.goods.service.GoodsSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("goodsSpecService")
@Transactional(readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class GoodsSpecServiceImpl implements GoodsSpecService {
	@Autowired
	private GoodsSpecDao goodsSpecDao;
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public GoodsSpecEntity queryObject(Long id){
		return goodsSpecDao.queryObject(id);
	}



	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Result queryList(Map<String, Object> map){
        Result result =  new Result();
		List<GoodsSpecEntity>	list = goodsSpecDao.queryList(map);
		result.setStatus(HttpConstants.SUCCESS);
		result.setMsg("查询规格成功");
		result.setData(list);
		return result;
	}
	
	@Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int queryTotal(Map<String, Object> map){
		return goodsSpecDao.queryTotal(map);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void save(GoodsSpecEntity goodsSpec){
		// 生成规格唯一id
		goodsSpec.setSpecNo(IdGenerate.generate(IdGenerate.SPEC_NO_PREFIX));
		goodsSpecDao.save(goodsSpec);
	}
	
	@Override
	public void update(GoodsSpecEntity goodsSpec){
		goodsSpecDao.update(goodsSpec);
	}
	
	@Override
	public void delete(Long id){
		goodsSpecDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		goodsSpecDao.deleteBatch(ids);
	}
	
}
