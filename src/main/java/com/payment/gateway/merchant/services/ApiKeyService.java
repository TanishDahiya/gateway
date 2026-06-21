package com.payment.gateway.merchant.services;


import com.payment.gateway.merchant.dto.request.ApiKeyCreationRequest;
import com.payment.gateway.merchant.dto.response.ApiKeyCreationResponse;
import com.payment.gateway.merchant.dto.response.ApiKeyResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreationResponse createApiKey(UUID merchantId,ApiKeyCreationRequest apiKeyCreationRequest);

    List<ApiKeyResponse> getAllApiKeys(UUID merchantId);

     void revokeApiKeys(UUID merchantId, UUID keyId);

    ApiKeyResponse rotateKey(UUID merchantId, UUID keyId,ApiKeyCreationRequest apiKeyCreationRequest);
}
