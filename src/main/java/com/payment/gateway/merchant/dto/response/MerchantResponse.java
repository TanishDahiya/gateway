package com.payment.gateway.merchant.dto.response;

import com.payment.gateway.common.enums.BusinessType;
import com.payment.gateway.common.enums.MerchantStatus;

import java.util.UUID;

public record MerchantResponse(

        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        MerchantStatus status

) {
}
