package com.bst.backcommon.util;

/**
 * 辅助类,不会抛出任何异常
 *
 * @author wanna
 * @date 2018-5-28
 */
public class QuietlyUtil {

    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable close : closeables) {
            try {
                close.close();
            } catch (Exception e) {
            }
        }
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }
}
