package com.payment.gateway.merchant.services.implementation;

import com.payment.gateway.common.enums.ErrorCode;
import com.payment.gateway.common.exception.ResourceNotFoundException;
import com.payment.gateway.common.utils.ApiKeyGenerator;
import com.payment.gateway.merchant.config.MerchantMapper;
import com.payment.gateway.merchant.dto.request.ApiKeyCreationRequest;
import com.payment.gateway.merchant.dto.response.ApiKeyCreationResponse;
import com.payment.gateway.merchant.dto.response.ApiKeyResponse;
import com.payment.gateway.merchant.entity.APIKey;
import com.payment.gateway.merchant.entity.Merchant;
import com.payment.gateway.merchant.repository.ApiKeyRepository;
import com.payment.gateway.merchant.repository.MerchantRepository;
import com.payment.gateway.merchant.services.ApiKeyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final MerchantRepository merchantRepository;
    private final MerchantMapper  merchantMapper;

    @Override
    @Transactional
    public ApiKeyCreationResponse createApiKey(UUID merchantId, ApiKeyCreationRequest apiKeyCreationRequest) {

        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow( () -> new ResourceNotFoundException(ErrorCode.MERCHANT_NOT_FOUND,"Merchant Not Found "+merchantId));

        String apiKeyId = ApiKeyGenerator.generateApiKeyId(apiKeyCreationRequest.environment());
        String secretKey = ApiKeyGenerator.generateSecretKey(apiKeyCreationRequest.environment());

        APIKey apiKey = APIKey.builder()
                .merchant(merchant)
                .keyId(apiKeyId)
                .keySecretHash(secretKey)
                .environment(apiKeyCreationRequest.environment())
                .build();

        apiKeyRepository.save(apiKey);

        return merchantMapper.toApiKeyCreationResponse(apiKey);

    }

    @Override
    public List<ApiKeyResponse> getAllApiKeys(UUID merchantId) {

        List<APIKey> apiKey = apiKeyRepository.findByMerchant_Id(merchantId);
        return merchantMapper.toApiKeyResponseList(apiKey);
        //return apiKey.stream().map(apiKey1 -> new ApiKeyResponse(apiKey1.getId(),apiKey1.isEnabled()...........))


    }

    @Override
    @Transactional
    public void revokeApiKeys(UUID merchantId, UUID keyId) {
//      will change with Spring Security

//        Method 3
        int updatedRows = apiKeyRepository.revokeApiKey(keyId, merchantId);

        if(updatedRows == 0){
            throw new ResourceNotFoundException(ErrorCode.KEY_NOT_FOUND, "Key not found : " + keyId);
        }


//      Method 2
//        APIKey apiKey = apiKeyRepository.findByIdAndMerchantId(keyId, merchantId)
//                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.KEY_NOT_FOUND, "Key not found : " + keyId));
//
//        apiKey.setEnabled(false);

//        Method 1
//        APIKey apiKey = apiKeyRepository.findById(keyId)
//                .filter(k -> k.getMerchant().getId().equals(merchantId))
//                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.KEY_NOT_FOUND,"Key Not Found "+keyId));
//        apiKey.setEnabled(false);


    }

    @Override
    @Transactional
    public ApiKeyResponse rotateKey(UUID merchantId, UUID keyId,ApiKeyCreationRequest apiKeyCreationRequest) {
       APIKey apiKey = apiKeyRepository.findByIdAndMerchantId(keyId,merchantId)
               .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.KEY_NOT_FOUND, "Key not found : " + keyId));

       apiKey.setPrevKeySecretHash(apiKey.getKeySecretHash());
       String secretKey = ApiKeyGenerator.generateSecretKey(apiKeyCreationRequest.environment());
       apiKey.setKeySecretHash(secretKey);
       apiKey.setRotatedAt(LocalDateTime.now());
       apiKey.setGracePeriodExpires(LocalDateTime.now().plusHours(24));
//       apiKeyRepository.save(apiKey);   // Need to enable when not wrapped in Transaction

       return merchantMapper.toApiKeyResponse(apiKey);

    }
}
