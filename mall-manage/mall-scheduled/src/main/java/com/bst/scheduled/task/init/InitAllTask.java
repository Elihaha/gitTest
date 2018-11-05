package com.bst.scheduled.task.init;

import com.bst.scheduled.service.impl.RedisOrderMonitoring;
import com.bst.scheduled.service.impl.CourierCompanyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class InitAllTask  implements CommandLineRunner {

    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    InitOrderToRedis initOrderToRedis;

    @Autowired
    CourierCompanyTask courierCompanyTask;

    @Autowired
    RedisOrderMonitoring redisOrderMonitoring;

    @Autowired
    InitGoodsTask initGoodsTask;


    @Override
    public void run(String... args) throws Exception {

//        threadPoolExecutor.execute(initOrderToRedis);
//        threadPoolExecutor.execute(courierCompanyTask);
        threadPoolExecutor.execute(redisOrderMonitoring);
        threadPoolExecutor.execute(initGoodsTask);

        threadPoolExecutor.shutdown();

    }
}
