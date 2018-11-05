package com.bst.backcommon.exception;

/**
 * 商城异常
 */
public class MallException extends RuntimeException{

    private static final long serialVersionUID = -3777837532573153284L;

    public MallException() {
        super();
    }

    public MallException(String message) {
        super(message);
    }

    public MallException(String message, Throwable cause) {
        super(message, cause);
    }

    public MallException(Throwable cause) {
        super(cause);
    }

    protected MallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
