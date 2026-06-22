package com.payment.gateway.merchant.dto.request;

import com.payment.gateway.common.enums.Environment;

public record ApiKeyCreationRequest(
        Environment environment
) {
}
