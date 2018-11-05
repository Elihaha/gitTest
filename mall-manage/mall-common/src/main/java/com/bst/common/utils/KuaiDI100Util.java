package com.bst.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bst.common.pojo.ComCode;
import com.bst.common.pojo.QueryKuaidiJson;
import com.bst.common.pojo.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
public class KuaiDI100Util {


    public static Optional<QueryKuaidiJson> kuaiDiQuery(String expressNo) {
//        #region 查询快递信息
        if (!StringUtils.isEmpty(expressNo)) {
            try {
                /***
                 * 1、https://m.kuaidi100.com/autonumber/auto?num=889999834560284183 获取到物流公司
                 * 2、https://m.kuaidi100.com/query?type=yuantong&postid=889999834560284183 获取物流数据
                 * 3、如果前面两步都没有得到数据，后期考虑直接抓起https://m.kuaidi100.com/result.jsp?nu=889999834560284183 里面的html数据
                 */
                String url = "https://m.kuaidi100.com/autonumber/auto?num=" + expressNo;
                ResultData errorMsg = ResultData.class.newInstance();
                if (HttpWebResponseUtility.createGetHttpResponse(url, errorMsg)) {
                    String message = errorMsg.getMessage();
                    if (KuaiDI100Util.isJSON2(message)) {
                        List<ComCode> list = JSONArray.parseArray(message, ComCode.class);
                        if (list != null && list.size() > 0) {
                            String comCode = list.get(0).getComCode();
                            url = "https://m.kuaidi100.com/query?type=" + comCode + "&postid=" + expressNo;
                            if (HttpWebResponseUtility.createGetHttpResponse(url, errorMsg)) {
                                //  这行没写  坑死我了  啊啊啊    重新获取返回信息
                                 message = errorMsg.getMessage();
                                if (KuaiDI100Util.isJSON2(message)) {
                                    QueryKuaidiJson jsonObject = JSON.parseObject(message,QueryKuaidiJson.class);
                                    return Optional.of(jsonObject);
                                }
                            } else {
                                log.info("kuaidi100获取数据：{}；{}",url,errorMsg.getMessage());
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                log.info("kuaidi100获取数据异常：{}",ex);
            }
        }
//              #endregion
        return Optional.empty();
    }

    public static boolean isJSON2(String str) {
        boolean result = false;
        try {
            Object obj = JSON.parse(str);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

}
