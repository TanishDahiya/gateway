package com.payment.gateway.payment.dto.response;

import com.payment.gateway.common.entity.Money;
import com.payment.gateway.common.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record OrderRecordResponse(
        UUID id,
        UUID merchantId,
        Money amount,
        Integer attempts,
        OrderStatus status,
        Map<String, Object> notes,
        LocalDateTime expiresAt
) {
}
