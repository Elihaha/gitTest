package com.bst.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.bst.backcommon.permission.PermissionInfoUtil;
import com.bst.backcommon.permission.entity.Operator;
import com.bst.common.config.Snowflake.SnowflakeId;
import com.bst.common.constants.CommonConstants;
import com.bst.common.constants.HttpConstants;
import com.bst.common.entity.goods.*;
import com.bst.common.mapper.goods.*;
import com.bst.common.mapper.shop.ShopInfoMapper;
import com.bst.common.modle.Result;
import com.bst.common.modle.goods.GoodsQuery;
import com.bst.common.modle.goods.GoodsRedisInfo;
import com.bst.common.modle.goods.GoodsResult;
import com.bst.common.modle.goods.SkuInfoQuery;
import com.bst.common.service.GoodsRedisService;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.NumberPrefix;
import com.bst.common.utils.RedisParam;
import com.bst.goods.model.*;
import com.bst.goods.service.GoodsSpuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.DoubleStream;

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

    @Autowired
    private GoodsRedisService goodsRedisService;

    @Autowired
    private JedisClusterUtils jedisCluster;

    @Autowired
    private GoodsSpuSpecMapper goodsSpuSpecMapper;

    @Autowired
    private GoodsSkuSpecValueMapper goodsSkuSpecValueMapper;




    @Autowired
    private RedisParam redisParam;


    /**
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
            /**得出商品最高价，最低价*/
            List<GoodsSkuSpecValueRequest> goodsSkuSpecValueList = request.getGoodsSkuSpecValueList();
            //最低价
            BigDecimal lowPrice = new BigDecimal(goodsSkuSpecValueList.stream().mapToDouble(object ->
                    object.getGoodsSkuPrice().doubleValue()
            ).min().getAsDouble());
            //最高价
            BigDecimal highPrice = new BigDecimal(goodsSkuSpecValueList.stream().mapToDouble(object ->
                    object.getGoodsSkuPrice().doubleValue()
            ).max().getAsDouble());

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
            spu.setLowPrice(lowPrice);
            spu.setHighPrice(highPrice);
            spu.setSoldoutCount(0);
            spu.setIsSell(false);
            //上架总数(新增时，就是剩余总库存)
            spu.setTotalPutaway(request.getTotalStock());
            spu.setGmtCreate(new Date());
            spu.setGmtUpdate(new Date());
            //保存spu记录
            goodsSpuMapper.insertSelective(spu);
            /**
             * 保存商品spu规格
             * */
           /* List<GoodsSpuSpec> goodsSpuSpecList = new ArrayList<>();
            for (Long specId : request.getSpecId()) {
                GoodsSpuSpec goodsSpuSpec = new GoodsSpuSpec();
                goodsSpuSpec.setSpecId(specId);
                //注意spu插入是否有返回spuId
                goodsSpuSpec.setSpuId(spu.getId());
                goodsSpuSpec.setGmtCreate(new Date());
                goodsSpuSpec.setGmtUpdate(new Date());
                goodsSpuSpecList.add(goodsSpuSpec);
            }
            goodsSpuSpecMapper.insertByList(goodsSpuSpecList);
            */
            /**保存商品sku和sku规格值*/
            List<SkuStockRedis> skuStockRedisList = new ArrayList<>();
            saveGoodsSkuAndGoodsSpec(goodsSkuSpecValueList, spu,skuStockRedisList);
            //批量保存商品图片
            List<String> images = request.getImagesList();
            GoodsImage image = null;
            List<GoodsImage> imageList = new ArrayList<GoodsImage>();
            for (String i : images) {
                image = new GoodsImage();
                image.setImageType(1);
                image.setImageUrl(i);
                image.setMainId(spu.getId());
                imageList.add(image);
            }
            if (imageList.size() > 0) {
                goodsImageMapper.insertBatchImages(imageList);
            }
            result.setStatus(HttpConstants.SUCCESS);
            result.setMsg("商品创建成功");
            result.setData(spu);
            List<GoodsSku> goodsSkuList = goodsSkuMapper.selectByspuId(spu.getId());
            //将创建的商品库存，存入redis中
            goodsRedisService.addCountBySpuNO(spu.getSpuNo(), spu.getTotalStock());
            for (GoodsSku goodsSku : goodsSkuList) {
                goodsRedisService.addCountBySkuNO(goodsSku.getSkuNo(), goodsSku.getStock());
            }
            return result;
        } catch (Exception e) {
            LOGGER.error(">>>>>>系统异常：", e);
            throw new RuntimeException();
        }
    }

    /**
     * 编辑商品
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public Result updateGoods(GoodsSpuRequest request) {
        try {
            GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(request.getGoodsId());
            if (goodsSpu == null) {
                return Result.error("当前商品不存在");
            }
            List<GoodsSku> goodsSku = goodsSkuMapper.selectByspuId(goodsSpu.getId());
            if (goodsSku == null) {
                return Result.error("当前商品规格不存在");
            }
            if (request == null || StringUtils.isEmpty(request.getGoodsName())
                    || StringUtils.isEmpty(request.getGoodsDetail()) || (request.getGoodsPrice() == null || "".equals(request.getGoodsPrice()))) {
                return Result.error("编辑商品失败：入参不完整");
            }
            if (request.getImagesList() == null || request.getImagesList().size() < 1) {
                return Result.error("编辑商品失败：缺少商品的展示图片");
            }
            GoodsSpu updateSpu = new GoodsSpu();
            BeanUtils.copyProperties(request, updateSpu);
            updateSpu.setId(goodsSpu.getId());
            updateSpu.setGmtUpdate(new Date());
            //重新编辑的新剩余库存
            Integer newTotalStock = request.getTotalStock();
            //之前的剩余库存
            Integer oldTotalStock = request.getOldTotalStock();
            //剩余库存差值
            int minus = newTotalStock - oldTotalStock;
            //编辑之后的总库存
            updateSpu.setTotalPutaway(goodsSpu.getTotalPutaway() + minus);
            //重新编辑之后的商品就是未上架状态
            updateSpu.setGoodsStatus((byte) CommonConstants.goodsShelfStatus);
            //当前操作人Id
            //获取当前登陆的用户
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            updateSpu.setOperatorId(operator.getId());
            //商品规格记录》》》》》》》》》》》》》》》》》》》》》》》
            GoodsSku sku = new GoodsSku();
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            /**得出商品最高价，最低价*/
            List<GoodsSkuSpecValueRequest> goodsSkuSpecValueList = request.getGoodsSkuSpecValueList();

            //最低价
            BigDecimal lowPrice = new BigDecimal(goodsSkuSpecValueList.stream().mapToDouble(object ->
                    object.getGoodsSkuPrice().doubleValue()
            ).min().getAsDouble());

            //最高价
            BigDecimal highPrice = new BigDecimal(goodsSkuSpecValueList.stream().mapToDouble(object ->
                    object.getGoodsSkuPrice().doubleValue()
            ).max().getAsDouble());

            updateSpu.setLowPrice(lowPrice);
            updateSpu.setHighPrice(highPrice);

            goodsSpuMapper.updateByPrimaryKeySelective(updateSpu);
            /**skuId和库存增量*/
            List<SkuStockRedis> skuStockRedisList = new ArrayList<>();
            /**整理sku*/
           // List<GoodsSku> goodsSkuList = updateSkuPriceAndTotalStock(request.getGoodsSkuSpecValueList(), updateSpu, skuStockRedisList);
            /** 更新sku的价格库存*/
            //goodsSkuMapper.updateSkuTotalAndPriceByList(goodsSkuList);
            //保存and更新spu表
            saveGoodsSkuAndGoodsSpec(request.getGoodsSkuSpecValueList(), updateSpu,skuStockRedisList);
            //处理商品的展示图
            //先把之前的对应照片删除，再保存
            goodsImageMapper.deleteByMainId(goodsSpu.getId());
            //批量保存商品图片
            List<String> images = request.getImagesList();
            GoodsImage image = null;
            List<GoodsImage> imageList = new ArrayList<GoodsImage>();
            for (String img : images) {
                image = new GoodsImage();
                image.setImageType(1);
                image.setImageUrl(img);
                image.setMainId(goodsSpu.getId());
                imageList.add(image);
            }
            if (imageList.size() > 0) {
                goodsImageMapper.insertBatchImages(imageList);
            }
            Result result = new Result();
            result.setStatus(HttpConstants.SUCCESS);
            result.setMsg("商品编辑成功");
            result.setData(updateSpu);

            //将spu库存和sku库存更新到redis中
            goodsRedisService.addCountBySpuNO(goodsSpu.getSpuNo(), minus);
            for(SkuStockRedis skuStockRedis : skuStockRedisList) {
                goodsRedisService.addCountBySkuNO(skuStockRedis.getSkuNo(),skuStockRedis.getMinus());
            }
            return result;
        } catch (Exception e) {
            LOGGER.error(">>>>>>系统异常：", e);
            throw new RuntimeException();
        }
    }

    /**
     * 查询商品详情
     *
     * @param id
     * @return
     */
    @Override
    public Result queryGoodsById(Long id) {
        try {
            GoodsSpuResponse response = new GoodsSpuResponse();
            GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(id);
            if (goodsSpu == null) {
                return Result.error("当前商品不存在");
            }
            BeanUtils.copyProperties(goodsSpu, response);
            response.setGoodsPrice(goodsSpu.getHighPrice());
            GoodsImage goodsImage = new GoodsImage();
            goodsImage.setImageType(1);
            goodsImage.setMainId(goodsSpu.getId());
            List<String> imageList = goodsImageMapper.selectByRecode(goodsImage);
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("goodsDetail", response);
            result.put("imagesList", imageList);
            return Result.ok(result);
        } catch (Exception e) {
            LOGGER.error(">>>>>>商品后台管理，查询商品详情异常：", e);
            return Result.error("查询商品详情失败：系统异常");
        }
    }

    /**
     * 查询当前用户下，所有的商品列表
     *
     * @return
     */
    @Override
    public Result queryGoodsList(GoodsPageQuery query) {
        try {
            Integer pageNumKey = query.getPageNumKey() == 0 ? 1 : query.getPageNumKey();
            Integer pageSizeKey = query.getPageSizeKey();
            Page page = PageHelper.startPage(pageNumKey, pageSizeKey);

            //当前所有商户下的
            //获取当前登陆的用户所在的商户
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            GoodsQuery goodsQuery = new GoodsQuery();
            if (CommonConstants.goolsSoldOutStatus.equals(query.getGoodsStatus())) {
                goodsQuery.setSoldOut(true);
            }
            goodsQuery.setStatusRange(CommonConstants.goolsSoldOutStatus.equals(query.getGoodsStatus()) ? null : query.getGoodsStatus());
            goodsQuery.setShopId(operator.getShopInfo().getId());
            goodsQuery.setGoodsName(query.getGoodsName());
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");


            if (StringUtils.isNotBlank(query.getStartUpdate())) {
                LocalDate startUpdate = LocalDate.parse(query.getStartUpdate(), f);
                LocalDateTime.of(startUpdate, LocalTime.MIN);
                goodsQuery.setStartUpdate(startUpdate);
            }
            if (StringUtils.isNotBlank(query.getEndUpdate())) {
                LocalDate endUpdate = LocalDate.parse(query.getEndUpdate(), f);
                LocalDateTime.of(endUpdate, LocalTime.MIN);
                goodsQuery.setEndUpdate(endUpdate);
            }
            List<GoodsResult> goodsResults = goodsSpuMapper.queryListByRecord(goodsQuery);
            Iterator<GoodsResult> goodsResultsIterator = goodsResults.iterator();
            while (goodsResultsIterator.hasNext()) {
                GoodsResult goodsResult = goodsResultsIterator.next();
                if (goodsResult.getLowPrice().equals(goodsResult.getHighPrice())) {
                    goodsResult.setPrice(goodsResult.getLowPrice() + "");
                } else {
                    goodsResult.setPrice(goodsResult.getHighPrice() + "-" + goodsResult.getLowPrice());
                }

                if (!StringUtils.isEmpty(goodsResult.getParentName())) {
                    goodsResult.setCategoryName(goodsResult.getParentName() + "-" + goodsResult.getCategoryName());
                }

                if (StringUtils.isNotBlank(query.getStartUpdate()) || StringUtils.isNotBlank(query.getEndUpdate())) {
                    if (goodsResult.getGoodsStatus() == (byte) CommonConstants.goodsShelfStatus ||
                            goodsResult.getGoodsStatus() == (byte) CommonConstants.goodsDeleteShelfStatus) {
                        goodsResultsIterator.remove();
                    }
                }
            }
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("goodsList", goodsResults);
            data.put("total", page.getTotal());
            data.put("pageNum", pageNumKey);
            return Result.ok(data);
        } catch (Exception e) {
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
        try {
            Result result = new Result();
            GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(request.getGoodsId());
            if (goodsSpu == null) {
                return Result.error("商品操纵失败：当前商品不存在");
            }
            //获取当前登陆的用户所在的商户
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();

            // 更新spu
            GoodsSpu updateSpu = updateSpu(request,operator,result);

            // 更新sku
            updateSku(request,updateSpu);

            // 更新缓存
            updateRedisSpu(request,goodsSpu,operator,result);

            result.setStatus(HttpConstants.SUCCESS);
            result.setData(null);
            return result;
        } catch (Exception e) {
            LOGGER.error(">>>>>>商品后台管理，商品编辑，异常：", e);
            throw new RuntimeException();
        }
    }

    /**
     *
     * 修改Spu
     * @param request 修改请求
     * @param operator 操作人
     * @param result 返回结果
     */
    private GoodsSpu updateSpu(GoodsSpuRequest request,Operator operator,Result result){
        GoodsSpu updateSpu = new GoodsSpu();
        updateSpu.setId(request.getGoodsId());
        updateSpu.setGoodsStatus(request.getGoodsStatus());
        updateSpu.setOperatorId(operator.getId());
        updateSpu.setGmtUpdate(new Date());
        // 修改Spu时间
        upateSpuTimeAndIsSell(updateSpu,updateSpu.getGoodsStatus(),result);
        goodsSpuMapper.updateByPrimaryKeySelective(updateSpu);
        return  updateSpu;
    }

    /**
     *  修改Redis缓存信息
     * @param request
     * @param goodsSpu
     * @param operator
     * @param result
     */
    private void updateRedisSpu(GoodsSpuRequest request,GoodsSpu goodsSpu,Operator operator,Result result){
        GoodsSpu redisGoods = new GoodsSpu();
        BeanUtils.copyProperties(goodsSpu, redisGoods);
        redisGoods.setOperatorId(operator.getId());
        redisGoods.setGoodsStatus(request.getGoodsStatus());
        // 修改Spu时间
        upateSpuTimeAndIsSell(redisGoods,redisGoods.getGoodsStatus(),result);
        //操作完商品状态存入redis中
        updateGoodsRedis(redisGoods);
    }


    /**
     *  修改sku
     * @param request
     * @param updateSpu
     */
    private void updateSku(GoodsSpuRequest request,GoodsSpu updateSpu){
        List<GoodsSku> skuList = new ArrayList<GoodsSku>();
        GoodsSku updateSku = new GoodsSku();
        //遍历GoodsSku，更新商品sku状态
        List<GoodsSku> goodsSkuList = goodsSkuMapper.selectByspuId(request.getGoodsId());
        for (GoodsSku goodsSku : goodsSkuList) {
            updateSku.setSkuStatus(request.getGoodsStatus());
            updateSku.setIsSell(updateSpu.getIsSell());
            updateSku.setSpuId(updateSpu.getId());
            updateSku.setGmtUpdate(new Date());
            skuList.add(updateSku);
        }
        goodsSkuMapper.updateBatchBySpuId(skuList);
    }

    /**
     *  根据修改状态更改Spu时间
     * @param updateSpu
     * @param goodsStatus
     * @param result
     */
    private  void upateSpuTimeAndIsSell(GoodsSpu updateSpu,Byte goodsStatus,Result result){
        Boolean isSell;
        if (goodsStatus == (byte) CommonConstants.goodsOnShelfStatus) {
            //上架
            isSell = true;
            updateSpu.setIsSell(isSell);
            updateSpu.setPutawayTime(new Date());
            result.setMsg("商品上架成功");
        } else if (goodsStatus == (byte) CommonConstants.goodsLowShelfStatus) {
            //下架
            isSell = false;
            updateSpu.setIsSell(isSell);
            updateSpu.setRemoveTime(new Date());
            result.setMsg("商品下架成功");
        } else {
            isSell = false;
            updateSpu.setIsSell(isSell);
            result.setMsg("商品删除成功");
        }
    }



    @Override
    @Transactional(value = "mallTransactionManager", rollbackFor = Exception.class)
    public Result batchUpdateGoodsStatus(GoodsSpuRequest request) {
        try {
            //获取当前登陆的用户所在的商户
            Operator operator = PermissionInfoUtil.getCurrentLogginUser();
            Result result = new Result();
            List<Long> ids = request.getIds();
            Byte goodsStatus = request.getGoodsStatus();
            List<GoodsSpu> spus = new ArrayList<GoodsSpu>();
            List<GoodsSku> skus = new ArrayList<GoodsSku>();
            Boolean isSell = false;
            String msg = new String();
            GoodsSpu spu = null;
            GoodsSku sku = null;
            List<GoodsSpu> spusRedis = new ArrayList<GoodsSpu>();
            for (Long id : ids) {
                GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(id);
                spu = new GoodsSpu();
                spu.setId(id);
                spu.setGoodsStatus(goodsStatus);
                spu.setGmtUpdate(new Date());
                goodsSpu.setGoodsStatus(goodsStatus);
                if (goodsStatus == (byte) CommonConstants.goodsOnShelfStatus) {
                    isSell = true;
                    spu.setPutawayTime(new Date());
                    goodsSpu.setPutawayTime(spu.getPutawayTime());
                    msg = "商品批量上架成功";
                } else if (goodsStatus == (byte) CommonConstants.goodsLowShelfStatus) {
                    isSell = false;
                    spu.setRemoveTime(new Date());
                    goodsSpu.setRemoveTime(spu.getRemoveTime());
                    msg = "商品批量下架成功";
                } else {
                    msg = "商品批量删除成功";
                }
                spu.setIsSell(isSell);
                goodsSpu.setIsSell(isSell);
                goodsSpu.setOperatorId(operator.getId());
                spu.setOperatorId(operator.getId());
                spus.add(spu);
                //遍历GoodsSku，更新商品pku状态
                List<GoodsSku> goodsSkuList = goodsSkuMapper.selectByspuId(id);
                for (GoodsSku goodsSku : goodsSkuList) {
                    sku = new GoodsSku();
                    sku.setSkuStatus(goodsStatus);
                    sku.setIsSell(isSell);
                    sku.setSpuId(spu.getId());
                    sku.setGmtUpdate(new Date());
                    skus.add(sku);
                }
                spusRedis.add(goodsSpu);
            }
            goodsSpuMapper.updateBatchById(spus);
            goodsSkuMapper.updateBatchBySpuId(skus);
            result.setMsg(msg);
            result.setStatus(HttpConstants.SUCCESS);
            result.setData(null);
            for (GoodsSpu g : spusRedis) {
                updateGoodsRedis(g);
            }
            return result;
        } catch (Exception e) {
            LOGGER.error(">>>>>>商品后台管理，商品批量操作，异常：", e);
            throw new RuntimeException();
        }
    }

    @Override
    public void updateBatchBySpuNo(String s, Integer parseInt) {
//        @Param("spuNo") String spuNo, @Param("number") Integer number
        Integer finalParseInt = parseInt;
        HashMap<String, Object> map = new HashMap<String, Object>(2) {{
            put("spuNo", s);
            put("number", finalParseInt);
        }};
        goodsSpuMapper.updateBatchBySpuNo(map);
        Object number = map.get("number");
        parseInt = (Integer) number;
    }


    /**
     * 将商品相关信息放入redis中
     * @param redisGoods
     */
    private void updateGoodsRedis(GoodsSpu redisGoods) {
        try {
            Long goodsId = redisGoods.getId();
            byte goodsStatus = redisGoods.getGoodsStatus();
            String key = redisParam.getGoodsSpuBaseInfoKey().replaceAll("_", ":") + ":" + goodsId;
            if (goodsStatus == (byte) CommonConstants.goodsDeleteShelfStatus) {
                //删除的商品
                jedisCluster.delkeyObject(key);
            } else {
                GoodsRedisInfo redisInfo = new GoodsRedisInfo();
                BeanUtils.copyProperties(redisGoods, redisInfo);
                redisInfo.setShopName(shopInfoMapper.selectByPrimaryKey(redisGoods.getShopId()).getShopName());
                List<Long> skuIds = goodsSkuMapper.getSkuIdsBySpuId(redisGoods.getId());
                redisInfo.setSkuIds(new HashSet<>(skuIds));
                //上架，保存到redis中
                GoodsImage goodsImage = new GoodsImage();
                goodsImage.setMainId(goodsId);
                goodsImage.setImageType(1);
                List<String> imageList = goodsImageMapper.selectByRecode(goodsImage);
                redisInfo.setImagesList(new HashSet<>(imageList));
                jedisCluster.set(key, JSON.toJSONString(redisInfo));
            }
        } catch (Exception e) {
            LOGGER.error(">>>>>>>>操作商品状态的时候，更新redis出错，异常信息：", e);
        }

    }

    /**
     * 增加商品sku规格和价格库存
     *
     * @return [goodsSkuSpecValueRequestsList, spu，List<SkuStockRedis> skuStockRedisList]
     */

    @Override
    public void saveGoodsSkuAndGoodsSpec(List<GoodsSkuSpecValueRequest> goodsSkuSpecValueRequestsList, GoodsSpu spu, List<SkuStockRedis> skuStockRedisList) {
        List<GoodsSku> goodsSkuList = new ArrayList<>();
        List<GoodsSkuSpecValue> goodsSkuSpecValues = new ArrayList<>();
        for (GoodsSkuSpecValueRequest goodsSkuSpecValueRequest : goodsSkuSpecValueRequestsList) {
            //商品规格记录
            GoodsSku sku = new GoodsSku();
            SkuStockRedis skuStockRedis = new SkuStockRedis();
            String skuNo = NumberPrefix.SKU_NO_PREFIX + SnowflakeId.getId();
            if (goodsSkuSpecValueRequest.getSkuNo() == null || goodsSkuSpecValueRequest.getSkuNo() == "") {
                sku.setSkuNo(skuNo);
            } else {
                sku.setSkuNo(goodsSkuSpecValueRequest.getSkuNo());
            }
            sku.setSkuName(spu.getGoodsName());
            sku.setCostPrice(goodsSkuSpecValueRequest.getGoodsSkuPrice());
            sku.setMarketPrice(goodsSkuSpecValueRequest.getGoodsSkuPrice());
            sku.setPricing(goodsSkuSpecValueRequest.getGoodsSkuPrice());
            sku.setSellPrice(goodsSkuSpecValueRequest.getGoodsSkuPrice());
            sku.setStock(goodsSkuSpecValueRequest.getGoodsSkuTotalStock());
            sku.setSpuId(spu.getId());
            sku.setShowWeight(0);
            sku.setSkuStatus((byte) CommonConstants.goodsShelfStatus);
            sku.setIsSell(false);
            sku.setSoldoutCount(0);
            sku.setGmtCreate(new Date());
            sku.setGmtUpdate(new Date());
            goodsSkuList.add(sku);

            /**
             * 整理出sku增加的库存数
             * */
            //暂时指定新剩余sku库存为旧库存数
            int skuNewTotalStock = 0;
            int skuOldTotalStock = 0;
            if (goodsSkuSpecValueRequest.getGoodsSkuTotalStock() != null) {

                /**重新编辑的新剩余sku库存*/
                skuNewTotalStock = goodsSkuSpecValueRequest.getGoodsSkuTotalStock();
            }
            /**之前的剩余sku旧库存*/
            if(goodsSkuSpecValueRequest.getSkuOldTotalStock()!=null) {
                skuOldTotalStock = goodsSkuSpecValueRequest.getSkuOldTotalStock();
            }
            //剩余库存差值
            int minus = skuNewTotalStock - skuOldTotalStock;
            skuStockRedis.setSkuNo(goodsSkuSpecValueRequest.getSkuNo());
            skuStockRedis.setMinus(minus);
            skuStockRedisList.add(skuStockRedis);


            //取出每组规格值id
            List<Long> specValueIdList = goodsSkuSpecValueRequest.getList();

            for (Long specValueId : specValueIdList) {
                GoodsSkuSpecValue goodsSkuSpecValue = new GoodsSkuSpecValue();

                if (goodsSkuSpecValueRequest.getSkuNo() == null || goodsSkuSpecValueRequest.getSkuNo() == "") {
                    goodsSkuSpecValue.setSkuNo(skuNo);
                } else {
                    goodsSkuSpecValue.setSkuNo(goodsSkuSpecValueRequest.getSkuNo());
                }
                /**g》》》》》》》》》》》》》该成skuNo*/
                // goodsSkuSpecValue.setSkuId(sku.getId());
                goodsSkuSpecValue.setSpecValueId(specValueId);
                goodsSkuSpecValue.setGmtCreate(new Date());
                goodsSkuSpecValue.setGmtUpdate(new Date());
                goodsSkuSpecValues.add(goodsSkuSpecValue);
            }
        }
        //保存sku
        goodsSkuMapper.insertGoodsSkuByList(goodsSkuList);
        goodsSkuSpecValueMapper.insertGoodsSkuSpecValueByList(goodsSkuSpecValues);
    }




    @Override
    public Set<SkuInfoQuery> getAllSpecSkuInfoBySpuNo(String key) {
        return goodsRedisService.getAllSpecSkuInfoBySpuNo(RedisParam.hashKey(redisParam.getKeyGoodsskuAll(),RedisParam.HASH)+key);
    }

    /**
     * @param
     * @return [list, updateSpu, spuRequest]
     */
    private List<GoodsSku> updateSkuPriceAndTotalStock(List<GoodsSkuSpecValueRequest> list, GoodsSpu updateSpu, List<SkuStockRedis> skuStockRedisList) {
        List<GoodsSku> goodsSkuList = new ArrayList<>();
        for (GoodsSkuSpecValueRequest goodsSkuRequest : list) {
            GoodsSku sku = new GoodsSku();
            SkuStockRedis skuStockRedis = new SkuStockRedis();
            sku.setSkuNo(goodsSkuRequest.getSkuNo());
            sku.setSkuName(updateSpu.getGoodsName());
            /**
             * 整理出sku增加的库存数
             * */
            //暂时指定新剩余sku库存为旧库存数
            int skuNewTotalStock = goodsSkuRequest.getSkuOldTotalStock();
            if (goodsSkuRequest.getGoodsSkuTotalStock() != null) {

                /**重新编辑的新剩余sku库存*/
                skuNewTotalStock = goodsSkuRequest.getGoodsSkuTotalStock();
            }
            /**之前的剩余sku旧库存*/
            int skuOldTotalStock = goodsSkuRequest.getSkuOldTotalStock();
            //剩余库存差值
            int minus = skuNewTotalStock - skuOldTotalStock;
            skuStockRedis.setSkuNo(goodsSkuRequest.getSkuNo());
            skuStockRedis.setMinus(minus);
            skuStockRedisList.add(skuStockRedis);

            sku.setStock(updateSpu.getTotalStock());
            sku.setGmtUpdate(new Date());
            sku.setId(updateSpu.getId());
            sku.setIsSell(false);
            sku.setCostPrice(goodsSkuRequest.getGoodsSkuPrice());
            sku.setMarketPrice(goodsSkuRequest.getGoodsSkuPrice());
            sku.setPricing(goodsSkuRequest.getGoodsSkuPrice());
            sku.setSellPrice(goodsSkuRequest.getGoodsSkuPrice());
            goodsSkuList.add(sku);
        }
        return goodsSkuList;
    }
}
