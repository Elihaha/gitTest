package com.bst.order.service.impl;

import com.bst.backcommon.permission.PermissionInfoUtil;
import com.bst.common.entity.order.PostageConfigEntity;
import com.bst.backcommon.permission.entity.Operator;
import com.bst.common.constants.HttpConstants;
import com.bst.common.mapper.order.PostageConfigDao;

import com.bst.common.pojo.Pagination;
import com.bst.common.modle.order.PostageConfigDto;
import com.bst.common.modle.order.PostageConfigInsertPojo;
import com.bst.common.utils.JedisClusterUtils;
import com.bst.common.utils.RedisParam;
import com.bst.order.service.PostageConfigService;
import com.bst.common.modle.Result;
import com.bst.common.modle.order.PostageConfigPojo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service("postageConfigService")
@Slf4j
@PropertySource(value = "classpath:application.yml", ignoreResourceNotFound = true)
@Transactional(value = "mallTransactionManager" ,readOnly = false, rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class PostageConfigServiceImpl implements PostageConfigService {

   @Autowired
   RedisParam redisParam;



    @Autowired
    private PostageConfigDao postageConfigDao;

    @Autowired
    private JedisClusterUtils jedisCluster;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Result queryObject(Long id) {
        PostageConfigEntity result = postageConfigDao.queryObject(id);
        if (Objects.isNull(result)) {
            Result.ok(HttpConstants.SUCCESS, "没有相关函数");
        }
        return Result.ok(result);
    }

    @Override
    public Result queryByProvince(String name) {
        try {
            Long id = PermissionInfoUtil.getCurrentLogginUser().getShopInfo().getId();
            return Result.ok(postageConfigDao.queryByProvince(name,id));
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(e.getMessage(),e);
            return Result.error(e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Result queryList(Pagination pagination, String like) {
        HashMap<String, Object> hashMap = getStringObjectHashMap(pagination, like);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //   获取 当前用户id 去数据库里面匹配
        Long id = PermissionInfoUtil.getCurrentLogginUser().getShopInfo().getId();
        hashMap.put("userID",id);

        List<PostageConfigDto> postageConfigEntities = postageConfigDao.queryPostageConfigPojoList(hashMap);

        int total = this.postageConfigDao.queryTotal(hashMap);
        return Result.ok(
                new HashMap<String, Object>() {{
                    put("total", total);
                    put("data", postageConfigEntities);
                }});

    }

    private HashMap<String, Object> getStringObjectHashMap(Pagination pagination,String like) {
        return new HashMap<String, Object>() {{
            Integer page = pagination.getPage();
            Integer number = pagination.getNumber();
            int value = (page - 1) * number;
            put("offset", value < 0 ? 0 : value);
            put("limit", number);
             if(StringUtils.isNotBlank(like)){
                 put("like",like);
             }
        }};
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Result queryTotal(Map<String, Object> map) {
        return Result.ok(postageConfigDao.queryTotal(map));
    }

    @Override
    public Result save(PostageConfigInsertPojo postageConfig) {
        //  从shiro  中获取  user id
        //   获取 当前用户id 去数据库里面匹配
        Operator currentLogginUser = PermissionInfoUtil.getCurrentLogginUser();
        Long id = currentLogginUser.getShopInfo().getId();
        Optional<String> hget = jedisCluster.hget(redisParam.getPostageConfig() + id, postageConfig.getProvince());
        if(!hget.isPresent()){
            jedisCluster.hset(redisParam.getPostageConfig() + id,postageConfig.getProvince(),postageConfig.getPostage().setScale(2, BigDecimal.ROUND_HALF_UP).toString());

            postageConfigDao.save(postageConfig.castPostageConfigEntity(currentLogginUser.getId(),id));
            return Result.ok();
        }
        log.error("省份已存在");
        return Result.error("省份已存在");
    }

    @Override
    public Result update(PostageConfigPojo postageConfig) {
        Subject subject = SecurityUtils.getSubject();
        Operator user = (Operator) subject.getPrincipal();
//        Integer id = user.getId();
//        Integer id = 2;
        Long id = user.getShopInfo().getId();
        PostageConfigEntity t = postageConfig.castPostageConfigEntity(Long.valueOf(user.getId()), id);
        t.setCreateTime(null);
        int update = postageConfigDao.update(t);
        jedisCluster.hset(redisParam.getPostageConfig() + id,postageConfig.getProvince(),postageConfig.getPostage().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        return Result.ok(update);
    }

    @Override
    public Result delete(Long id) {
        return Result.ok(postageConfigDao.delete(id));
    }

    @Override
    public Result deleteBatch(Long[] ids) {
        return Result.ok(postageConfigDao.deleteBatch(ids));
    }

}
