package com.payment.gateway.merchant.services;

import com.payment.gateway.merchant.dto.request.MerchantSignupRequest;
import com.payment.gateway.merchant.dto.response.MerchantResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

public interface AuthService {
    MerchantResponse signup(MerchantSignupRequest merchantSignupRequest);
}
