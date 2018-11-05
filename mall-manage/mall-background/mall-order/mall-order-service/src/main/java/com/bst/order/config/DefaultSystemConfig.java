package com.bst.order.config;

import com.bst.backcommon.conditional.DefaultSystemUploadPath;
import com.bst.backcommon.conditional.liunx.LinuxCondition;
import com.bst.backcommon.conditional.liunx.LinuxUploadPath;
import com.bst.backcommon.conditional.windows.WindowsConditional;
import com.bst.backcommon.conditional.windows.WindowsUploadPath;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/19 23:04 2018 09
 */
@Configuration
public class DefaultSystemConfig {


    @Bean("windowsUploadPath")
    @Conditional(WindowsConditional.class)
    public DefaultSystemUploadPath windowsSystemConfig(){
        return new WindowsUploadPath();
    }

    @Bean("linuxUploadPath")
    @Conditional(LinuxCondition.class)
    public DefaultSystemUploadPath linuxSystemConfig(){
        return new LinuxUploadPath();
    }


}
