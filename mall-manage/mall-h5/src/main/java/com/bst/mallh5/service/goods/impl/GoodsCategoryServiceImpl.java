package com.bst.mallh5.service.goods.impl;

import com.bst.common.entity.goods.GoodsCategory;
import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.mapper.goods.GoodsCategoryMapper;
import com.bst.common.mapper.goods.GoodsSpuMapper;
import com.bst.common.modle.Result;
import com.bst.mallh5.model.goods.CategoryCode;
import com.bst.common.modle.goods.GoodsSpuCategoryResponse;
import com.bst.mallh5.model.goods.CategoryQueryResponse;
import com.bst.mallh5.service.goods.GoodsCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zouqiang
 * @create 2018-11-01 10:35
 **/
@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsCategoryServiceImpl.class);

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private GoodsSpuMapper goodsSpuMapper;

    /**
     * 查询商品分类
     */

    @Override
    public Result queryGoodsCategory(Long pid, Long shopId) {
        Result result = new Result();
        try {
            GoodsCategory goodsCategory = new GoodsCategory();
            if (pid == null || "".equals(pid)) {
                //默认0为一级分类
                goodsCategory.setPid(CategoryCode.THEDEFAULTLEVEL);
            } else {
                goodsCategory.setPid(pid);
            }
            goodsCategory.setShopId(shopId);
            List<GoodsCategory> list =  goodsCategoryMapper.queryListByCateGory(goodsCategory);
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

    /**
     * 查询出分类下的商品
     */
    @Override
    public Result querySpuByCategoryId(Long id, Long shopId, Integer page, Integer rows) {
        Result result = new Result();
        //图片类型 2 为缩略图
        Byte imageType = CategoryCode.IMAGETYPE;
        // 1 商品状态为上架
        Byte goodsStatus = CategoryCode.GOODSSTATUS;
        try {
            PageHelper.startPage(page, rows);
            List<GoodsSpuCategoryResponse> list = goodsSpuMapper.queryByCategoryIdAndShopId(id, shopId, imageType, goodsStatus);
            PageInfo<GoodsSpuCategoryResponse> pageInfo = new PageInfo<>(list);
            Long total = pageInfo.getTotal();
            Map<String, Object> map = new HashMap<>();
            if (list.size() == 0) {
                //该分类下没有商品
                result.setStatus(502);
                result.setMsg("fail");
                return result;
            }
            map.put("list", list);
            map.put("total", total);
            result.setData(map);
            result.setStatus(200);
            result.setMsg("success");
            return result;
        } catch (Exception ex) {
            LOGGER.error("查询该分类下商品错误", ex);
            throw new RuntimeException();
        }
    }

    @Override
    public Result queryAllSpuByShopId(Long shopId, Integer page, Integer rows) {
        Result result = new Result();
        //图片类型 2 为缩略图
        Byte imageType = CategoryCode.IMAGETYPE;
        // 1 商品状态为上架
        Byte goodsStatus = CategoryCode.GOODSSTATUS;

        Long id = null;
        try {
            PageHelper.startPage(page, rows);
            List<GoodsSpuCategoryResponse> list = goodsSpuMapper.queryByCategoryIdAndShopId(id, shopId, imageType, goodsStatus);
            PageInfo<GoodsSpuCategoryResponse> info = new PageInfo<>(list);
            Long total = info.getTotal();
            Map<String, Object> map = new HashMap<>();
            if (list.size() == 0) {
                //该分类下没有商品
                result.setStatus(502);
                result.setMsg("fail");
                return result;
            }
            map.put("list", list);
            map.put("total", total);
            result.setData(map);
            result.setStatus(200);
            result.setMsg("success");
            return result;
        } catch (Exception ex) {
            LOGGER.error("查询所有商品错误", ex);
            throw new RuntimeException();
        }
    }

    /**
     * 递归查询所有分类
     */
    @Override
    public Result queryAllCategory(Long pid ,Long shopId) {
        Result result = new Result();
        try {
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setShopId(shopId);
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

}
