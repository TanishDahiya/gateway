package com.payment.gateway.common.exception;

import com.payment.gateway.common.enums.ErrorCode;

public class DuplicateResourceException extends RuntimeException {

    private final ErrorCode errorCode;

    public DuplicateResourceException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
