package com.bst.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.bst.backcommon.permission.PermissionInfoUtil;
import com.bst.backcommon.permission.entity.Operator;
import com.bst.common.config.Snowflake.SnowflakeId;
import com.bst.common.constants.CommonConstants;
import com.bst.common.constants.HttpConstants;
import com.bst.common.entity.goods.GoodsImage;
import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.entity.goods.GoodsSpu;
import com.bst.common.mapper.goods.GoodsImageMapper;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.common.mapper.goods.GoodsSpuMapper;
import com.bst.common.mapper.shop.ShopInfoMapper;
import com.bst.common.modle.Result;
import com.bst.common.modle.goods.GoodsQuery;
import com.bst.common.modle.goods.GoodsRedisInfo;
import com.bst.common.modle.goods.GoodsResult;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.utils.NumberPrefix;
import com.bst.goods.model.GoodsPageQuery;
import com.bst.goods.model.GoodsSpuRequest;
import com.bst.goods.model.GoodsSpuResponse;
import com.bst.goods.service.GoodsSpuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.*;

/**
 * @author lumin
 * @description:
 * @create 2018-09-17 17:31
 **/
@Service("goodsSpuService")
public class GoodsSpuServiceImpl implements GoodsSpuService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsSpuServiceImpl.class);

    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    @Autowired
    private GoodsSpuMapper goodsSpuMapper;

    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Autowired
    private ShopInfoMapper shopInfoMapper;

    @Value("${fastDFS.config.httpServerPath}")
    private String httpServerPath;

    @Autowired
    private GoodsRedisService goodsRedisService;

    @Autowired
    private JedisCluster jedisCluster;

    @Value("${redisKey.config.mallGoodsSpuBaseInfo}")
    private String goodsSpuBaseInfoKey;

    /**
     *
     * @param request 商品的基本参数
     * @return
     */
    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public Result saveGoods(GoodsSpuRequest request) {
        try {
            Result result = new Result();
            //获取当前登陆的用户
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            //商品记录
            GoodsSpu spu = new GoodsSpu();
            BeanUtils.copyProperties(request, spu);
            spu.setId(null);
            //生成唯一的商品编号
            spu.setSpuNo(NumberPrefix.SPU_NO_PREFIX + SnowflakeId.getId());
            //商户Id
            spu.setShopId(operator.getShopInfo().getId());
            //操作Id
            spu.setOperatorId(operator.getId());
            //商品状态（0未上架，1上架，2已下架）
            spu.setGoodsStatus((byte) CommonConstants.goodsShelfStatus);
            spu.setLowPrice(request.getGoodsPrice());
            spu.setHighPrice(request.getGoodsPrice());
            spu.setSoldoutCount(0);
            spu.setIsSell(false);
            //上架总数(新增时，就是剩余总库存)
            spu.setTotalPutaway(request.getTotalStock());
            spu.setGmtUpdate(new Date());
            //保存spu记录
            goodsSpuMapper.insertSelective(spu);
            //商品规格记录
            GoodsSku sku = new GoodsSku();
            sku.setSkuNo(NumberPrefix.SKU_NO_PREFIX+ SnowflakeId.getId());
            sku.setSkuName(spu.getGoodsName());
            sku.setCostPrice(spu.getHighPrice());
            sku.setMarketPrice(spu.getHighPrice());
            sku.setPricing(spu.getHighPrice());
            sku.setSellPrice(spu.getHighPrice());
            sku.setStock(spu.getTotalStock());
            sku.setSpuId(spu.getId());
            sku.setShowWeight(0);
            sku.setSkuStatus((byte) CommonConstants.goodsShelfStatus);
            sku.setIsSell(false);
            sku.setSoldoutCount(0);
            sku.setGmtUpdate(new Date());
            //保存商品规格记录
            goodsSkuMapper.insertSelective(sku);

            //批量保存商品图片
            List<String> images=request.getImagesList();
            GoodsImage image =null;
            List<GoodsImage> imageList = new ArrayList<GoodsImage>();
            for(String i : images){
                image = new GoodsImage();
                image.setImageType(1);
                image.setImageUrl(i);
                image.setMainId(spu.getId());
                imageList.add(image);
            }
            if(imageList.size()>0){
                goodsImageMapper.insertBatchImages(imageList);
            }
            result.setStatus(HttpConstants.SUCCESS);
            result.setMsg("商品创建成功");
            result.setData(spu);
            //将创建的商品库存，存入redis中
            goodsRedisService.addCountBySpuNO(spu.getSpuNo(),spu.getTotalStock());
            goodsRedisService.addCountBySkuNO(sku.getSkuNo(),sku.getStock());
            return result;
        } catch (Exception e) {
            LOGGER.error(">>>>>>系统异常：", e);
            throw new RuntimeException();
        }
    }

    /**
     * 编辑商品
     * @param request
     * @return
     */
    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public Result updateGoods(GoodsSpuRequest request) {
        try{
            GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(request.getGoodsId());
            if(goodsSpu == null){
                return Result.error("当前商品不存在");
            }
            GoodsSku goodsSku = goodsSkuMapper.selectByspuId(goodsSpu.getId());
            if(goodsSku == null){
                return Result.error("当前商品规格不存在");
            }
            if(request==null || StringUtils.isEmpty(request.getGoodsName())
                    || StringUtils.isEmpty(request.getGoodsDetail()) || StringUtils.isEmpty(request.getGoodsPrice())){
                return Result.error("编辑商品失败：入参不完整");
            }
            if(request.getImagesList()==null || request.getImagesList().size()<1){
                return Result.error("编辑商品失败：缺少商品的展示图片");
            }
            GoodsSpu updateSpu = new GoodsSpu();
            BeanUtils.copyProperties(request,updateSpu);
            updateSpu.setId(goodsSpu.getId());
            updateSpu.setGmtUpdate(new Date());
            //重新编辑的新剩余库存
            Integer newTotalStock = request.getTotalStock();
            //之前的剩余库存
            Integer oldTotalStock = request.getOldTotalStock();
            //剩余库存差值
            int minus =newTotalStock-oldTotalStock;
            //编辑之后的总库存
            updateSpu.setTotalPutaway(goodsSpu.getTotalPutaway()+minus);
            //重新编辑之后的商品就是未上架状态
            updateSpu.setGoodsStatus((byte) CommonConstants.goodsShelfStatus);
            //当前操作人Id
            //获取当前登陆的用户
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            updateSpu.setOperatorId(operator.getId());
            //商品规格记录
            GoodsSku sku = new GoodsSku();
            sku.setSkuName(updateSpu.getGoodsName());
            sku.setStock(updateSpu.getTotalStock());
            sku.setGmtUpdate(new Date());
            sku.setId(goodsSku.getId());
            sku.setIsSell(false);
            if(request.getGoodsPrice().compareTo(goodsSpu.getHighPrice())!=0){
                //价格变化了
                updateSpu.setLowPrice(request.getGoodsPrice());
                updateSpu.setHighPrice(request.getGoodsPrice());
                sku.setCostPrice(updateSpu.getHighPrice());
                sku.setMarketPrice(updateSpu.getHighPrice());
                sku.setPricing(updateSpu.getHighPrice());
                sku.setSellPrice(updateSpu.getHighPrice());
            }
            goodsSpuMapper.updateByPrimaryKeySelective(updateSpu);
            goodsSkuMapper.updateByPrimaryKeySelective(sku);

            //处理商品的展示图
            //先把之前的对应照片删除，再保存
            goodsImageMapper.deleteByMainId(goodsSpu.getId());
            //批量保存商品图片
            List<String> images=request.getImagesList();
            GoodsImage image =null;
            List<GoodsImage> imageList = new ArrayList<GoodsImage>();
            for(String img : images){
                image = new GoodsImage();
                image.setImageType(1);
                image.setImageUrl(img);
                image.setMainId(goodsSpu.getId());
                imageList.add(image);
            }
            if(imageList.size()>0){
                goodsImageMapper.insertBatchImages(imageList);
            }
            Result result = new Result();
            result.setStatus(HttpConstants.SUCCESS);
            result.setMsg("商品编辑成功");
            result.setData(updateSpu);

            //将spu库存和sku库存更新到redis中
            goodsRedisService.addCountBySpuNO(updateSpu.getSpuNo(),minus);
            goodsRedisService.addCountBySkuNO(goodsSku.getSkuNo(),minus);
            return result;
        }catch (Exception e){
            LOGGER.error(">>>>>>系统异常：", e);
            throw new RuntimeException();
        }
    }

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    @Override
    public Result queryGoodsById(Long id) {
        try{
            GoodsSpuResponse response = new GoodsSpuResponse();
            GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(id);
            if(goodsSpu == null){
                return Result.error("当前商品不存在");
            }
            BeanUtils.copyProperties(goodsSpu,response);
            response.setGoodsPrice(goodsSpu.getHighPrice());
            GoodsImage goodsImage = new GoodsImage();
            goodsImage.setImageType(1);
            goodsImage.setMainId(goodsSpu.getId());
            List<String> imageList = goodsImageMapper.selectByRecode(goodsImage);
            Map<String, Object> result = new HashMap<String,Object>();
            result.put("goodsDetail",response);
            result.put("imagesList",imageList);
            return Result.ok(result);
        }catch (Exception e){
            LOGGER.error(">>>>>>商品后台管理，查询商品详情异常：", e);
            return Result.error("查询商品详情失败：系统异常");
        }
    }

    /**
     * 查询当前用户下，所有的商品列表
     * @return
     */
    @Override
    public Result queryGoodsList(GoodsPageQuery query) {
        try {
            Integer pageNumKey = query.getPageNumKey()==0?1:query.getPageNumKey();
            Integer pageSizeKey = query.getPageSizeKey();
            Page page = PageHelper.startPage(pageNumKey, pageSizeKey);
            StringBuffer goodsStatus = new StringBuffer();
            if(query.getGoodsStatus()==3){
                goodsStatus.append("0,1,2");
            }else{
                goodsStatus.append(query.getGoodsStatus());
            }
            //当前所有商户下的
            //获取当前登陆的用户所在的商户
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            GoodsQuery goodsQuery = new GoodsQuery();
            goodsQuery.setStatusRange(goodsStatus.toString());
            goodsQuery.setShopId(operator.getShopInfo().getId());
            goodsQuery.setGoodsName(query.getGoodsName());
            List<GoodsResult> goodsResults = goodsSpuMapper.queryListByRecord(goodsQuery);
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("goodsList",goodsResults);
            data.put("total",page.getTotal());
            data.put("pageNum",pageNumKey);
            return Result.ok(data);
        }catch (Exception e){
            LOGGER.error(">>>>>>商品后台管理，分页查询商品列表，异常：", e);
            return Result.error("分页查询商品列表失败：系统异常");
        }
    }

    /**
     * 商品的1上架/2下架/3删除操作
     * @param request
     * @return
     */
    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public Result updateGoodsStatus(GoodsSpuRequest request) {
        try{
            Result result = new Result();
            Long goodsId = request.getGoodsId();
            Byte goodsStatus = request.getGoodsStatus();
            GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(goodsId);
            if(goodsSpu == null){
                return Result.error("商品操纵失败：当前商品不存在");
            }
            GoodsSpu updateSpu = new GoodsSpu();
            updateSpu.setId(goodsId);
            updateSpu.setGoodsStatus(goodsStatus);

            GoodsSpu redisGoods = new GoodsSpu();
            BeanUtils.copyProperties(goodsSpu,redisGoods);
            //获取当前登陆的用户所在的商户
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            GoodsSku updateSku = new GoodsSku();
            updateSpu.setOperatorId(operator.getId());
            redisGoods.setOperatorId(updateSpu.getOperatorId());
            redisGoods.setGoodsStatus(goodsStatus);
            Boolean isSell =false;
            if(goodsStatus == (byte)CommonConstants.goodsOnShelfStatus){
                //上架
                updateSpu.setIsSell(true);
                updateSpu.setPutawayTime(new Date());
                redisGoods.setPutawayTime(new Date());
                isSell = true;
                result.setMsg("商品上架成功");
            }else if(goodsStatus== (byte) CommonConstants.goodsLowShelfStatus){
                //下架
                updateSpu.setIsSell(false);
                updateSpu.setRemoveTime(new Date());
                redisGoods.setRemoveTime(updateSpu.getRemoveTime());
                isSell = false;
                result.setMsg("商品下架成功");
            }else{
                updateSpu.setIsSell(false);
                result.setMsg("商品删除成功");
            }

            List<GoodsSku> skuList = new ArrayList<GoodsSku>();
            updateSku.setSkuStatus(goodsStatus);
            updateSku.setIsSell(isSell);
            redisGoods.setIsSell(isSell);
            updateSku.setSpuId(goodsSpu.getId());
            updateSku.setGmtUpdate(new Date());
            skuList.add(updateSku);
            goodsSpuMapper.updateByPrimaryKeySelective(updateSpu);
            goodsSkuMapper.updateBatchBySpuId(skuList);

            //操作完商品状态存入redis中
            updateGoodsRedis(redisGoods);
            result.setStatus(HttpConstants.SUCCESS);
            result.setData(null);
            return  result;
        }catch (Exception e){
            LOGGER.error(">>>>>>商品后台管理，商品编辑，异常：", e);
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public Result batchUpdateGoodsStatus(GoodsSpuRequest request) {
        try{
            //获取当前登陆的用户所在的商户
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            Result result = new Result();
            List<Long> ids = request.getIds();
            Byte goodsStatus = request.getGoodsStatus();
            List<GoodsSpu> spus = new ArrayList<GoodsSpu>();
            List<GoodsSku> skus = new ArrayList<GoodsSku>();
            Boolean isSell =false;
            String msg = new String();
            GoodsSpu spu =null;
            GoodsSku sku = null;
            List<GoodsSpu> spusRedis = new ArrayList<GoodsSpu>();
            for(Long id : ids){
                GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(id);
                spu = new GoodsSpu();
                sku = new GoodsSku();
                spu.setId(id);
                spu.setGoodsStatus(goodsStatus);
                goodsSpu.setGoodsStatus(goodsStatus);
                if(goodsStatus ==  (byte)CommonConstants.goodsOnShelfStatus){
                    isSell = true;
                    spu.setPutawayTime(new Date());
                    goodsSpu.setPutawayTime(spu.getPutawayTime());
                    msg="商品批量上架成功";
                }else if(goodsStatus == (byte) CommonConstants.goodsLowShelfStatus){
                    isSell = false;
                    spu.setRemoveTime(new Date());
                    goodsSpu.setRemoveTime(spu.getRemoveTime());
                    msg="商品批量下架成功";
                }else{
                    msg="商品批量删除成功";
                }
                spu.setIsSell(isSell);
                goodsSpu.setIsSell(isSell);
                goodsSpu.setOperatorId(operator.getId());
                spu.setOperatorId(operator.getId());
                spus.add(spu);
                sku.setSkuStatus(goodsStatus);
                sku.setIsSell(isSell);
                sku.setSpuId(spu.getId());
                sku.setGmtUpdate(new Date());
                skus.add(sku);
                spusRedis.add(goodsSpu);
            }
            goodsSpuMapper.updateBatchById(spus);
            goodsSkuMapper.updateBatchBySpuId(skus);
            result.setMsg(msg);
            result.setStatus(HttpConstants.SUCCESS);
            result.setData(null);
            for(GoodsSpu g : spusRedis) {
                updateGoodsRedis(g);
            }
            return result;
        }catch (Exception e){
            LOGGER.error(">>>>>>商品后台管理，商品批量操作，异常：", e);
            throw new RuntimeException();
        }
    }

    /**
     * 将商品相关信息放入redis中
     * @param redisGoods
     */
    private void updateGoodsRedis(GoodsSpu redisGoods){
        try{
            Long goodsId = redisGoods.getId();
            byte goodsStatus = redisGoods.getGoodsStatus();
            String key = goodsSpuBaseInfoKey.replaceAll("_",":")+":"+goodsId;
            if(goodsStatus == (byte)CommonConstants.goodsDeleteShelfStatus){
                //删除的商品
                jedisCluster.del(key);
            }else{
                GoodsRedisInfo redisInfo = new GoodsRedisInfo();
                BeanUtils.copyProperties(redisGoods,redisInfo);
                redisInfo.setShopName(shopInfoMapper.selectByPrimaryKey(redisGoods.getShopId()).getShopName());
                //上架，保存到redis中
                GoodsImage goodsImage = new GoodsImage();
                goodsImage.setMainId(goodsId);
                goodsImage.setImageType(1);
                List<String> imageList = goodsImageMapper.selectByRecode(goodsImage);
                redisInfo.setImagesList(imageList);
                jedisCluster.set(key, JSON.toJSONString(redisInfo));
            }
        }catch (Exception e){
            LOGGER.error(">>>>>>>>操作商品状态的时候，更新redis出错，异常信息：",e);
        }

    }

}
