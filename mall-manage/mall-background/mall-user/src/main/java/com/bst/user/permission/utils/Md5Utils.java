package com.bst.user.permission.utils;


import org.apache.shiro.codec.Hex;

import java.security.MessageDigest;
import java.util.Random;

public class Md5Utils {  
    /** 
     * 生成含有随机盐的密码 
     */  
    public static String generate(String password,String salt) {  
       
        password = md5Hex(password + salt);  
       
        return password;  
    }  
    /**
     * 获取盐
     * @return
     */
    public static String getSalt() {
    	   Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
           sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));  
           int len = sb.length();  
           if (len < 16) {  
               for (int i = 0; i < 16 - len; i++) {  
                   sb.append("0");  
               }  
           }  
           String salt = sb.toString();  
           return salt;
	}

	/** 
     * 校验密码是否正确 
     */  
    public static boolean verify(String password, String passwordDb,String salt) {  
     
        return md5Hex(password + salt).equals(new String(passwordDb));  
    }  
  
    /** 
     * 获取十六进制字符串形式的MD5摘要 
     */  
    public static String md5Hex(String src) {  
        try {  
            MessageDigest md5 = MessageDigest.getInstance("MD5");  
            byte[] bs = md5.digest(src.getBytes());  
            return new String(new Hex().encode(bs));
        } catch (Exception e) {  
            return null;  
        }  
    }  
  
    public static void main(String[] args) {  
    	String salt=getSalt();
    	System.out.println(salt);
        String old=generate("zw1078679553", salt);
        System.out.println(old);
       System.out.println(verify("zw1078679553", old, salt)); 
       
    }  
}