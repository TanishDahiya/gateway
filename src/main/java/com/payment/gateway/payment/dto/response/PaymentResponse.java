package com.payment.gateway.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.payment.gateway.common.entity.Money;
import com.payment.gateway.common.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponse(
        UUID id,
        UUID merchantId,
        UUID orderId,
        Money amount,
        PaymentStatus paymentStatus,
        Map<String,Object> methodDetails,
        String bankReference,
        String errorCode,
        String errorDescription,
        Long refundAmountPaise,
        LocalDateTime createdAt,
        LocalDateTime capturedAt
) {
}
