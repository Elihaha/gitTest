package com.bst.order.service.impl;

import com.bst.common.modle.order.OrderChildGenerate;
import com.bst.order.service.OrdersManageService;
import com.bst.server.MallBackServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/28 21:16 2018 09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallBackServerApplication.class)
public class OrdersManageServiceImplTest {
    @Autowired
    OrdersManageService ordersManageService;

    @Test
    public void createOrderChild() {
            OrderChildGenerate order494816176719790081 = OrderChildGenerate.builder().mainNo("order494816176719790081").explanation("11").logisticsCompany("shunfeng").number(8).remarks("what").trackingNumber("1212312").build();
        ordersManageService.createOrderChild(order494816176719790081);
    }
}