package com.bst.common.exception;

/**
 * 图片处理异常
 * @author zhang zhiye
 */
public class ImageHandleException extends RuntimeException{
    private static final long serialVersionUID = -6590313648097632036L;

    public ImageHandleException() {
        super();
    }

    public ImageHandleException(String message) {
        super(message);
    }

    public ImageHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageHandleException(Throwable cause) {
        super(cause);
    }

    protected ImageHandleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
