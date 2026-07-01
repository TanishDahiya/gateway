package com.payment.gateway.payment.dto.request;

import com.payment.gateway.common.entity.Money;
import com.payment.gateway.common.enums.PaymentMethod;
import com.payment.gateway.common.enums.PaymentStatus;

import java.util.Map;
import java.util.UUID;

public record PaymentGatewayRequest(
        UUID paymentId,
        UUID merchantId,
        UUID orderId,
        PaymentMethod paymentMethod,
        Money amount,
        PaymentStatus paymentStatus,
        Map<String,Object> methodDetails
) {
}
