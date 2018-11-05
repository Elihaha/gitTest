package com.bst;

import com.bst.common.mapper.order.CourierCompanyMapper;
import com.bst.common.modle.order.PostageConfigInsertPojo;
import com.bst.goods.service.GoodsSpuService;
import com.bst.order.service.PostageConfigService;
import com.bst.order.service.ProvinceCityService;
import com.bst.order.service.impl.OrderLogisticsServiceImpl;
import com.bst.server.MallBackServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

import java.math.BigDecimal;
import java.util.HashMap;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallBackServerApplication.class)
public class MallBackServerApplicationTests {

    @Autowired
    JedisCluster jedisCluster;

    @Autowired
    GoodsSpuService goodsSpuService;

    @Autowired
    CourierCompanyMapper courierCompany;

    @Autowired
    ProvinceCityService provinceCityService;

    @Autowired
    PostageConfigService postageConfigService;

    @Autowired
    public OrderLogisticsServiceImpl orderLogisticsService;
        @Test
    public void contextLoads() {
//
//            List<OrderLogisticsVO> signerPhone = orderLogisticsService.queryList(new Pagination(1, 10), new HashMap<String, Object>() {{
//                put("signerPhone", "152");
//            }});
//            signerPhone.forEach(System.out::println);
//            System.out.println(jedisCluster.get(RedisParam.COURIER_COMPANY_CODE + "zhongtong"));
//        String sing="SPUf4034d88-62be-4d39-b46d-3c891b5edb46";
//        String key = RedisParam.KEY_GOODSSPU + sing;
////        jedisCluster.set(key,200+"");
//        String s = jedisCluster.get(key);
//        System.out.println(s);
//        Long aLong = jedisCluster.decrBy(key, 50);
//        System.out.println(aLong);
//        Integer integer=50;
//          goodsSpuService.updateBatchBySpuNo(sing, integer);
//        System.out.println(integer);

        provinceCityService.queryList(new HashMap<>()).forEach(provinceCityEntity -> {
            try {
                postageConfigService.save(PostageConfigInsertPojo.builder().postage(new BigDecimal(0.0)).province(provinceCityEntity.getName()).status(0).build());
            } catch (Exception e) {
//                e.printStackTrace();
                  log.error(e.getMessage(),e);
            }
        });

//        courierCompany.insert(CourierCompany.builder().id(SnowflakeId.getId()).code("shunfeng").name("顺丰快递").platform("kuaidi100").createDatetime(LocalDateTime.now()).updateDatetime(LocalDateTime.now()).build());
//        courierCompany.insert(CourierCompany.builder().id(SnowflakeId.getId()).code("yuantong").name("圆通快递").platform("kuaidi100").createDatetime(LocalDateTime.now()).updateDatetime(LocalDateTime.now()).build());
//        courierCompany.insert(CourierCompany.builder().id(SnowflakeId.getId()).code("shentong").name("申通快递").platform("kuaidi100").createDatetime(LocalDateTime.now()).updateDatetime(LocalDateTime.now()).build());
//        courierCompany.insert(CourierCompany.builder().id(SnowflakeId.getId()).code("zhongtong").name("中通快递").platform("kuaidi100").createDatetime(LocalDateTime.now()).updateDatetime(LocalDateTime.now()).build());
//        courierCompany.insert(CourierCompany.builder().id(SnowflakeId.getId()).code("wanxiang").name("万象物流").platform("kuaidi100").createDatetime(LocalDateTime.now()).updateDatetime(LocalDateTime.now()).build());
//        courierCompany.insert(CourierCompany.builder().id(SnowflakeId.getId()).code("huitongkuaidi").name("百世快递").platform("kuaidi100").createDatetime(LocalDateTime.now()).updateDatetime(LocalDateTime.now()).build());
//        courierCompany.insert(CourierCompany.builder().id(SnowflakeId.getId()).code("tiantian").name("天天快通").platform("kuaidi100").createDatetime(LocalDateTime.now()).updateDatetime(LocalDateTime.now()).build());
//        courierCompany.insert(CourierCompany.builder().id(SnowflakeId.getId()).code("youzhengguonei").name("中国邮政").platform("kuaidi100").createDatetime(LocalDateTime.now()).updateDatetime(LocalDateTime.now()).build());
//        courierCompany.insert(CourierCompany.builder().id(SnowflakeId.getId()).code("ems").name("邮政特快专递").platform("kuaidi100").createDatetime(LocalDateTime.now()).updateDatetime(LocalDateTime.now()).build());
//        courierCompany.insert(CourierCompany.builder().id(SnowflakeId.getId()).code("zhaijisong").name("宅急送").platform("kuaidi100").createDatetime(LocalDateTime.now()).updateDatetime(LocalDateTime.now()).build());
//        courierCompany.insert(CourierCompany.builder().id(SnowflakeId.getId()).code("debangwuliu").name("德邦物流").platform("kuaidi100").createDatetime(LocalDateTime.now()).updateDatetime(LocalDateTime.now()).build());
//
//        courierCompany.list(new HashMap<>()).stream().map(courierCompany1 -> ExpressTypes.builder().code(courierCompany1.getCode()).name(courierCompany1.getName()).build()).forEach(courierCompany1 -> {
//            System.out.println(courierCompany1);
//        });
//        mainNo  主订单编号    explanation  说明     number 数量  remarks   备注    trackingNumber   物流编号  logisticsCompany  物流公司
    }

}
