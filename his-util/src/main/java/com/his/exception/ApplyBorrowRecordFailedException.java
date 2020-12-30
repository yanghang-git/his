package com.his.exception;

import java.io.Serializable;

/**
 * Description: 申请借用车辆失败的异常类
 * Date: 20-12-29
 *
 * @author yh
 */
public class ApplyBorrowRecordFailedException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -3499995915622677298L;

    public ApplyBorrowRecordFailedException() {
        super();
    }

    public ApplyBorrowRecordFailedException(String message) {
        super(message);
    }

    public ApplyBorrowRecordFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplyBorrowRecordFailedException(Throwable cause) {
        super(cause);
    }

    protected ApplyBorrowRecordFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}