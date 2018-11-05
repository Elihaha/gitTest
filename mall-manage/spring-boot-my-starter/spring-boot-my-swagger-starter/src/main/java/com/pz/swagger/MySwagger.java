package com.pz.swagger;

import com.pz.swagger.SwaggerApplication;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author pz
 * @version 0.1
 * @date 2018/8/2 10:20
 * @email 226804871@qq.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SwaggerApplication.class)
@Inherited
public @interface MySwagger {

}
