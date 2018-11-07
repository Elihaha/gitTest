package com.bst;

import com.bst.common.entity.goods.GoodsImage;
import com.bst.common.entity.goods.GoodsSku;
import com.bst.common.mapper.goods.GoodsImageMapper;
import com.bst.common.mapper.goods.GoodsSkuMapper;
import com.bst.server.MallBackServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallBackServerApplication.class)

public class PostageTest {

    @Autowired
    private GoodsImageMapper goodsImageMapper;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(PostageTest.class);
    @Test
    public void imageTest(){
        List<GoodsImage> imageList = new ArrayList<>();
        GoodsImage goodsImage1 = new GoodsImage();
        goodsImage1.setMainNo("123");
        goodsImage1.setImageType(4);
        goodsImage1.setImageUrl("123大");
        goodsImage1.setCreateTime(new Date());
        imageList.add(goodsImage1);
        GoodsImage goodsImage2 = new GoodsImage();
        goodsImage2.setMainNo("456");
        goodsImage2.setImageType(4);
        goodsImage2.setImageUrl("456123");
        goodsImage2.setCreateTime(new Date());
        imageList.add(goodsImage2);
        goodsImageMapper.insertAndUpdateByMainNo(imageList);
    }

    @Test
    public void getUrlTest() {
        try {
            Long spuId = 59L;

        List<GoodsSku> skuIdList = goodsSkuMapper.selectByspuId(spuId);
        List<String> skuNoList = new ArrayList<>();
        for (GoodsSku goodsSku : skuIdList) {
            skuNoList.add(goodsSku.getSkuNo());
        }
        List<String> specPicList = goodsImageMapper.queryUrlBySkuNo(skuNoList);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + specPicList);
    }catch (Exception ex){
            LOGGER.error("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<",ex);
        }
    }

}
