package com.payment.gateway.common.exception;

import com.payment.gateway.common.enums.ErrorCode;

public class BuisnessRuleViolationException extends RuntimeException {

    private final ErrorCode errorCode;

    public BuisnessRuleViolationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
