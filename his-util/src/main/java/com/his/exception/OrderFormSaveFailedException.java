package com.his.exception;

import java.io.Serializable;

/**
 * Description: 订单添加失败的异常类
 * Date: 20-12-29
 *
 * @author yh
 */
public class OrderFormSaveFailedException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -3499995915622677298L;

    public OrderFormSaveFailedException() {
        super();
    }

    public OrderFormSaveFailedException(String message) {
        super(message);
    }

    public OrderFormSaveFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderFormSaveFailedException(Throwable cause) {
        super(cause);
    }

    protected OrderFormSaveFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}