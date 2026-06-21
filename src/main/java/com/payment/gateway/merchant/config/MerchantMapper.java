package com.payment.gateway.merchant.config;

import com.payment.gateway.merchant.dto.request.ApiKeyCreationRequest;
import com.payment.gateway.merchant.dto.response.ApiKeyCreationResponse;
import com.payment.gateway.merchant.dto.response.ApiKeyResponse;
import com.payment.gateway.merchant.dto.response.MerchantResponse;
import com.payment.gateway.merchant.entity.APIKey;
import com.payment.gateway.merchant.entity.Merchant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MerchantMapper {
    MerchantResponse toMerchantResponse(Merchant merchant);
    ApiKeyCreationResponse toApiKeyCreationResponse(APIKey apiKey);
    ApiKeyResponse toApiKeyResponse(APIKey apiKey);
    List<ApiKeyResponse> toApiKeyResponseList(List<APIKey> apiKeys);
}
