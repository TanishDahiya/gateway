package com.payment.gateway.common.enums;

public enum PaymentStatus {
    CREATED,
    AUTHORIZED,
    AUTHORIZING,
    CAPTURED,
    CAPTURING,
    FAILED,
    REFUNDED,
    CANCELED,
    PARTIALLY_REFUNDED,
    SETTLED,
    AUTH_EXPIRED
}
