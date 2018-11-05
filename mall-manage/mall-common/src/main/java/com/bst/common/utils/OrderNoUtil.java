package com.bst.common.utils;

import com.bst.common.config.Snowflake.SnowflakeId;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * 单号管理器
 */
public class OrderNoUtil {

    /**
     * 最大支持1-9个集群机器部署
     */
    private static Integer machineId;

    @Value("${server.machine-id}")
    public void setMachineId(Integer machineId) {
        OrderNoUtil.machineId = machineId;
    }

    //创建订单编号
    private static String createOrderNo() {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        return machineId + String.format("%015d", hashCodeV);
    }

    /**
     * 创建对象编号
     * @param prefix 对象前缀
     * @return 编号
     */
    public static String createObjectNo(String prefix) {
//        if (StringUtils.isBlank(prefix)) {
//            return createOrderNo();
//        }
        return prefix + SnowflakeId.getId();
    }
}
