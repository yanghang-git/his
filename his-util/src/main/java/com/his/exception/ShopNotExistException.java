package com.his.exception;

/**
 * Description: 自定义异常。  店铺不存在(店铺被删除用户无法登入)
 * Date: 20-12-21
 *
 * @author yh
 */
public class ShopNotExistException extends RuntimeException {
    private static final long serialVersionUID = 2014398396062745207L;

    public ShopNotExistException() {
        super();
    }

    public ShopNotExistException(String message) {
        super(message);
    }

    public ShopNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopNotExistException(Throwable cause) {
        super(cause);
    }

    protected ShopNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}