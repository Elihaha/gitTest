package com.bst.goods.service.impl;

import com.bst.backcommon.permission.PermissionInfoUtil;
import com.bst.backcommon.permission.entity.Operator;
import com.bst.common.entity.goods.GoodsCategory;
import com.bst.common.mapper.goods.GoodsCategoryMapper;
import com.bst.common.modle.Result;
import com.bst.goods.model.CategoryPageQuery;
import com.bst.goods.service.GoodsCategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zouqiang
 * @create 2018-09-19 12:05
 **/
@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl implements GoodsCategoryService{
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsCategoryServiceImpl.class);
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;
    public GoodsCategory queryGoodsCategory(Long id){
        return goodsCategoryMapper.selectByPrimaryKey(id);
    }



   public Result    queryGoodsCategoryList(CategoryPageQuery categoryPageQuery){
        try {

            Integer pageNumKey = categoryPageQuery.getPageNumKey()==0?1:categoryPageQuery.getPageNumKey();
            Integer pageSizeKey = categoryPageQuery.getPageSizeKey();
            Page page = PageHelper.startPage(pageNumKey, pageSizeKey);
            //StringBuffer goodsStatus = new StringBuffer();

            //当前所有商户下的
            //获取当前登陆的用户所在的商户
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            Map<String,Object> map = new HashMap();
           // map.put("status",categoryPageQuery.status);
            map.put("categoryName",categoryPageQuery.getCategoryName());
            List<GoodsCategory> categoryResults = goodsCategoryMapper.queryListByRecord(map);

            Map<String,Object> data = new HashMap<String,Object>();
            data.put("categoryResults",categoryResults);
            data.put("operator",operator.getName());
            data.put("total",page.getTotal());
            data.put("pageNum",pageNumKey);
            return Result.ok(data);
        }catch (Exception e){
            LOGGER.error(">>>>>>类品后台管理，分页查询类品列表，异常：", e);
            return Result.error("分页查询类品列表失败：系统异常");
        }
   }


}
