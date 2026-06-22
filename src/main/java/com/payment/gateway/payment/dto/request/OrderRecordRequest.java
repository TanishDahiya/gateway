package com.payment.gateway.payment.dto.request;

import com.payment.gateway.common.entity.Money;
import com.payment.gateway.common.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Map;

public record OrderRecordRequest(

        Money amount,
        String receipt,
        Map<String, Object> notes,
        LocalDateTime expiresAt
) {
}
