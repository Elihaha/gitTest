package com.bst.backcommon.conditional.liunx;

import com.bst.backcommon.conditional.DefaultSystemUploadPath;

import java.io.File;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/19 23:11 2018 09
 */
public class LinuxUploadPath implements DefaultSystemUploadPath {
    @Override
    public String getCurrentSystemUploadPath(){
        return System.getProperty("user.dir") + File.separator + "LogisticsExcels";
    }
}
