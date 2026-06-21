package com.payment.gateway.merchant.dto.response;

import com.payment.gateway.common.enums.Environment;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiKeyResponse(
        UUID id,
        String keyId,
        Environment environment,
        boolean enabled,
        LocalDateTime createdAt,
        LocalDateTime rotatedAt

) {
}
