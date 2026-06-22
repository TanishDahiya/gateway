package com.payment.gateway.merchant.dto.response;

import com.payment.gateway.common.enums.Environment;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiKeyCreationResponse(

        UUID id,
        String keyId,
        String keySecretHash,
        Environment environment,
        LocalDateTime createdAt,
        LocalDateTime rotatedAt

) {
}
