package com.his.exception;

import java.io.Serializable;

/**
 * Description: 订单quxiao失败的异常类
 * Date: 20-12-29
 *
 * @author yh
 */
public class OrderFormCancelFailedException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -3499995865622677298L;

    public OrderFormCancelFailedException() {
        super();
    }

    public OrderFormCancelFailedException(String message) {
        super(message);
    }

    public OrderFormCancelFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderFormCancelFailedException(Throwable cause) {
        super(cause);
    }

    protected OrderFormCancelFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}