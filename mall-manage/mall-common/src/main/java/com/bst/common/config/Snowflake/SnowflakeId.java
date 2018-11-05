package com.bst.common.config.Snowflake;

/**
 * @author pz
 * @version 0.1
 * @date 2018/8/10 15:01
 * @email 226804871@qq.com
 */
public class SnowflakeId {
    public static  SnowflakeIdWorker snowflakeIdWorker=new SnowflakeIdWorker();

    public static Long getId(){
        return snowflakeIdWorker.nextId();
    }
}
