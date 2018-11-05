package com.bst;

import com.bst.common.config.Snowflake.SnowflakeId;
import com.bst.common.modle.order.OrderLogisticsDto;
import com.bst.common.modle.order.PostageConfigPojo;
import com.bst.common.pojo.ResultData;
import com.bst.common.utils.HttpWebResponseUtility;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class PostageTest {

    @Test
    public void UIOmBg(){
        System.out.println(
                PostageConfigPojo
                        .builder()
                        .postage(new BigDecimal(99.99).setScale(2,   BigDecimal.ROUND_HALF_UP))
                        .province("长沙")
                        .status(0)
                        .build()
        );


        System.out.println(
                OrderLogisticsDto
                        .builder()
                        .address("不知名的地方")
                        .area("芙蓉区")
                        .signer("pz")
                        .signerPhone("17608413342")
                        .city("长沙市")
                        .postcode("41000")
                        .province("湖南")
                        .goodsCount(2)
                        .goodsName("test")
//                        .orderId(1232143124141341L)
                        .dhl("yuantong")
                        .trackingNumber(889999834560284183L+"")
                        .build()
        );



    }



    @Test
    public void  aVoid(){
//         Long javaLong=4941-9119-0363-6029-44L;
//         Long jsLong  =4941-9119-0363-6029-40L;
//         Long  sss    =4942-0202-8554-1908-48L;
//
//        System.out.println(SnowflakeId.getId());
        Integer integer = null;
        System.out.println(Stream.of(5, 6, 7, 20).anyMatch(s -> s.equals(integer)));
    }
    @Test
    public void  asasda(){
//         Long javaLong=4941-9119-0363-6029-44L;
//         Long jsLong  =4941-9119-0363-6029-40L;
//         Long  sss    =4942-0202-8554-1908-48L;
//
//        System.out.println(SnowflakeId.getId());
        Integer integer = null;
        System.out.println(Stream.of(5, 6, 7, 20).anyMatch(s -> s.equals(integer)));
    }

    @Test
    public void asdasda(){

        ResultData errorMgs = new ResultData();
        HttpWebResponseUtility.createGetHttpResponse("https://www.kuaidi100.com/query?type=shunfeng&postid=289283464329&temp=0.936491380151816", errorMgs);
        System.out.println(errorMgs);
    }
}
