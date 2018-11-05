package com.bst.backcommon.conditional.windows;

import com.bst.backcommon.conditional.DefaultSystemUploadPath;

import java.io.File;

/**
 * @author pengzhen
 * @email pengzhen
 * @date 2018/9/19 23:12 2018 09
 */
public class WindowsUploadPath implements DefaultSystemUploadPath {
    @Override
    public String getCurrentSystemUploadPath() {
        return "C:"+ File.separator+"Users"+ File.separator+"Public";
    }
}
