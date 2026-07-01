package com.payment.gateway.payment.dto.request;

import com.payment.gateway.common.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record PaymentRequest(

        @NotNull(message = "Order ID is required")
        UUID orderId,

        PaymentMethod paymentMethod,

        Map<String,Object> methodDetails

) {
}
