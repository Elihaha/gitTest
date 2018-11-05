package com.bst.backcommon.util;

import java.util.UUID;

/**
 * @author wanna
 * @date 2018-5-28
 */
public class IdUtil {

    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}
