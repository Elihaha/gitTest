package com.bst.user.permission.initCache;

import com.bst.user.permission.shop.service.ICacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 这个类是在项目启动后自动添加商品信息入缓存
 * @Author: Xia Zhengxin
 * @Date: 2018/11/6 15:51
 * @Version 1.0
 */
@Component
public class InitCache implements ApplicationRunner {
    @Autowired
    private ICacheService cache;

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 这个方法是执行缓存的方法
     * @param args
     */
    @Override
    @Async
    public void run(ApplicationArguments args) {
            try {
                cache.initShopInfoCache();
            }catch (Exception e){
                logger.error("缓存添加失败:"+e);
            }
    }
}
