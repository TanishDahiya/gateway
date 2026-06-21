package com.payment.gateway.common.exception;

public record ValidationError(
        String field,
        String message
) {
}
