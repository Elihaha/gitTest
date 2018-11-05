package com.bst.user.permission.utils;

/**
 * @author gan
 * @desc:  返回状态码
 */
public class StatusCodeConstants {
    /**
     * 没有权限
     */
    public static final Integer NOT_PERMISSION_EXCEPTION_CODE = 30000;
    /**
     * 账号或密码错误
     */
    public static final Integer USERNAME_OR_PASSWORD_ERROR_CODE = 20000;
    /**
     * 用户未登陆
     */
    public static final Integer USER_NOT_LOGGIN_CODE = 10000;
    /**
     * 请求成功状态码
     */
    public static final Integer REQUEST_SUCCESS_CODE = 200;
    /**
     * 系统异常
     */
    public static final Integer UNKNOWN_EXCEPTION_CODE = 500;
    /**
     * 生成二维码失败
     */
    public static final Integer FAILED_2_GENERATE_QR_CODE = 501;

}
