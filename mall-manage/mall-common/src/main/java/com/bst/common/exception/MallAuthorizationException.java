package com.bst.common.exception;

/**
 * 认证异常
 */
public class MallAuthorizationException extends RuntimeException{
    private static final long serialVersionUID = -5422280593991893592L;

    public MallAuthorizationException() {
        super();
    }

    public MallAuthorizationException(String message) {
        super(message);
    }

    public MallAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MallAuthorizationException(Throwable cause) {
        super(cause);
    }

    protected MallAuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
