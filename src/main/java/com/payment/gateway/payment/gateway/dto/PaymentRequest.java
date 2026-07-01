package com.payment.gateway.payment.gateway.dto;

import com.payment.gateway.common.entity.Money;
import com.payment.gateway.common.enums.PaymentMethod;

import java.util.Map;
import java.util.UUID;

public record PaymentRequest(
        UUID paymentId,
        UUID orderId,
        UUID merchantId,
        Money amount,
        PaymentMethod paymentMethod,
        Map<String,Object> methodDetails

) {
}
