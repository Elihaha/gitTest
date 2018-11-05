package com.bst.goods.service.impl;

import com.bst.backcommon.permission.PermissionInfoUtil;
import com.bst.backcommon.permission.entity.Operator;
import com.bst.common.entity.goods.GoodsCategory;
import com.bst.common.mapper.goods.GoodsCategoryMapper;
import com.bst.common.modle.Result;
import com.bst.goods.model.CategoryCode;
import com.bst.goods.model.CategoryQueryResponse;
import com.bst.goods.service.GoodsCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author zouqiang
 * @create 2018-09-19 12:05
 **/
@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsCategoryServiceImpl.class);
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public Result queryGoodsCategory(Long pid) {
        Result result = new Result();
        try {
            GoodsCategory goodsCategory = new GoodsCategory();
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            goodsCategory.setShopId(operator.getShopInfo().getId());
            if (pid == null || "".equals(pid)) {
                //默认0为一级分类
                goodsCategory.setPid(CategoryCode.THEDEFAULTLEVEL);
            } else {
                goodsCategory.setPid(pid);
            }
            List<GoodsCategory> list = goodsCategoryMapper.queryListByCateGory(goodsCategory);
            result.setStatus(200);
            result.setData(list);
            result.setMsg("success");
            return result;
        } catch (Exception ex) {
            LOGGER.error("查询分类错误", ex);
            result.setMsg("fail");
            result.setStatus(500);
            return result;
        }
    }

    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public GoodsCategory addGoodsCategoryByPid(Long pid, String name) {
        try {
            GoodsCategory goodsCategory = new GoodsCategory();

            goodsCategory.setCategoryName(name);
            //默认0为一级分类 -1为公共一级分类
            //分类级别
            if (CategoryCode.THEDEFAULTLEVEL.equals(pid) || CategoryCode.THEALLDEFAULTLEVEL.equals(pid)) {
                goodsCategory.setGrade((byte) 1);
            } else {
                //查出父类节点的分类级别
                GoodsCategory parentCategory = goodsCategoryMapper.selectByPrimaryKey(pid);
                Integer intGrade = (int) parentCategory.getGrade() + 1;
                byte byteGrade = intGrade.byteValue();
                goodsCategory.setGrade(byteGrade);
            }
            goodsCategory.setParent(false);
            goodsCategory.setPid(pid);
            goodsCategory.setGmtCreate(new Date());
            goodsCategory.setGmtUpdate(new Date());
            if (CategoryCode.THEALLDEFAULTLEVEL.equals(pid)) {
                //ShopId为 -1L 此分类为公共分类
                goodsCategory.setShopId(-1L);
            } else {
                Operator operator = PermissionInfoUtil.getCurrentLogginUser();
                goodsCategory.setShopId(operator.getShopInfo().getId());
            }
            goodsCategoryMapper.insertSelective(goodsCategory);

            return goodsCategory;
        } catch (Exception ex) {
            LOGGER.error("分类添加错误", ex);
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public int updateCategoryByPid(Long id, String name) {
        try {
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(id);
            goodsCategory.setCategoryName(name);
            goodsCategory.setGmtUpdate(new Date());
            int sax = goodsCategoryMapper.updateByPrimaryKey(goodsCategory);
            return sax;
        } catch (Exception ex) {
            LOGGER.error("分类更新错误", ex);
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public Result deleteCategoryByPid(Long id) {
        Result result = new Result();
        try {
            //删除此节点的所有子分类
            goodsCategoryMapper.deleteByPid(id);
            //查出此节点
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(id);
            Long pid = goodsCategory.getPid();
            Long shopId = goodsCategory.getShopId();
            //删除该节点
            goodsCategoryMapper.deleteByPrimaryKey(id);
            //根据此节点的pid查出兄弟节点
            List<GoodsCategory> brotherCategoryList = goodsCategoryMapper.queryListByPid(pid, shopId);
            //查出父节点
            GoodsCategory parentCategory = goodsCategoryMapper.selectByPrimaryKey(pid);
            if (null == brotherCategoryList || brotherCategoryList.size() == 0) {
                //修改父节点为非父节点
                parentCategory.setParent(false);
                goodsCategoryMapper.updateByPrimaryKeySelective(parentCategory);
            }
            result.setStatus(200);
            result.setMsg("success");
            return result;
        } catch (DataIntegrityViolationException ex) {
            LOGGER.error("分类主表约束删除错误》》》》》》》》》》》》》》》", ex);
            result.setStatus(CategoryCode.DATAINTEGRITYVIOLATION);
            result.setMsg("fail");
            return result;
        } catch (Exception exx) {
            LOGGER.error("分类删除错误", exx);
            result.setMsg("fail");
            result.setStatus(500);
            return result;
        }

    }

    /**
     * 递归查询所有分类
     */
    private List<CategoryQueryResponse> queryAllCategory(GoodsCategory category) {

        List<CategoryQueryResponse> list = new ArrayList<>();
        try {

            List<GoodsCategory> cateGoryList = goodsCategoryMapper.queryListByCateGory(category);
            if (cateGoryList != null && !cateGoryList.isEmpty()) {
                for (GoodsCategory cateVo : cateGoryList) {
                    CategoryQueryResponse categoryQueryResponse = new CategoryQueryResponse();
                    categoryQueryResponse.setId(cateVo.getId());
                    categoryQueryResponse.setCategoryName(cateVo.getCategoryName());
                    //是否为父节点
                    Boolean isParent = cateVo.getParent();

                    if (isParent) {
                        cateVo.setPid(cateVo.getId());
                        categoryQueryResponse.setCatQueryList(queryAllCategory(cateVo));
                    }
                    list.add(categoryQueryResponse);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询商品分类失败" + e.getMessage(), e);
        }
        return list;
    }

    /**
     * 递归查询所有分类
     */
    @Override
    public Result queryAllCategory(Long pid) {
        Result result = new Result();
        try {
            GoodsCategory goodsCategory = new GoodsCategory();
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            goodsCategory.setShopId(operator.getShopInfo().getId());
            if (pid == null) {
                //默认0为一级分类
                goodsCategory.setPid(CategoryCode.THEDEFAULTLEVEL);
            } else {
                goodsCategory.setPid(pid);
            }
            List<CategoryQueryResponse> list = queryAllCategory(goodsCategory);
            result.setStatus(200);
            result.setData(list);
            result.setMsg("success");
            return result;
        } catch (Exception ex) {
            LOGGER.error("查询分类错误", ex);
            result.setMsg("fail");
            result.setStatus(500);
            return result;
        }
    }

}
