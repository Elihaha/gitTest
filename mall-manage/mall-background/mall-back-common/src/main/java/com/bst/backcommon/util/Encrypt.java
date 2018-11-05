package com.bst.backcommon.util;


import java.security.MessageDigest;

/**
 * 获取加密串
 *
 * @author wanna
 * @date 2018-5-25
 */
public class Encrypt {

    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    public static String md5(String string) {

        try {
            MessageDigest md5 = MessageDigest.getInstance(Constant.MD5);
            byte[] bytes = string.getBytes(Constant.UTF8);
            byte[] digest = md5.digest(bytes);
            return format(digest);
        } catch (Exception e) {
            throw new RuntimeException("MD5 出错");
        }
    }

    public static String sha1(String string) {
        try {
            MessageDigest sha = MessageDigest.getInstance(Constant.SHA1);
            byte[] bytes = string.getBytes(Constant.UTF8);
            return format(sha.digest(bytes));
        } catch (Exception e) {
            throw new RuntimeException("SHA-1 出错");
        }
    }

    public static String sha256(String string) {
        try {
            MessageDigest sha = MessageDigest.getInstance(Constant.SHA256);
            byte[] bytes = string.getBytes(Constant.UTF8);
            return format(sha.digest(bytes));
        } catch (Exception e) {
            throw new RuntimeException("SHA-256 出错");
        }
    }

    private static String format(byte[] bytes) {
        StringBuilder builder = new StringBuilder(bytes.length * 2);

        for (int i = 0; i < bytes.length; i++) {
            builder.append(DIGITS[(bytes[i] >> 4) & 0x0f]);
            builder.append(DIGITS[bytes[i] & 0x0f]);
        }
        return builder.toString();
    }

}
