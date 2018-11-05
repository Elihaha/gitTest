package com.bst.common.modle.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 由于前端传递过来的是日期字符串格式的值，而SpringMVC无法将日期字符串格式的值解析为日期格式GoodsServiceImpl
 * 本文来自 成wp 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/pingweicheng/article/details/80690847?utm_source=copy
 * @author zouqiang
 * @create 2018-09-25 13:22
 **/

@Component
class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //设置日期格式
        ObjectMapper objectMapper = new ObjectMapper();
    	/*SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
    	objectMapper.setDateFormat(smt);*/
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        //设置中文编码格式
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        converters.add(mappingJackson2HttpMessageConverter);
    }


}
