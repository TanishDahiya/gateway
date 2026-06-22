package com.payment.gateway.merchant.controller;

import com.payment.gateway.merchant.dto.request.ApiKeyCreationRequest;
import com.payment.gateway.merchant.dto.response.ApiKeyCreationResponse;
import com.payment.gateway.merchant.dto.response.ApiKeyResponse;
import com.payment.gateway.merchant.services.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchants/{merchantId}/api-keys")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<ApiKeyCreationResponse> createApiKey(@PathVariable UUID merchantId, @Valid @RequestBody ApiKeyCreationRequest apiKeyCreationRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                apiKeyService.createApiKey(merchantId,apiKeyCreationRequest)
        );
    }

    @GetMapping
    public ResponseEntity<List<ApiKeyResponse>>  getAllApiKeys(@PathVariable UUID merchantId){
        return ResponseEntity.status(HttpStatus.OK).body(
                apiKeyService.getAllApiKeys(merchantId)
        );
    }

    @DeleteMapping("/{keyId}")
    public ResponseEntity<Void> revokeApiKey(@PathVariable UUID merchantId, @PathVariable UUID keyId){

        apiKeyService.revokeApiKeys(merchantId, keyId);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/{keyId}/rotateKey")
    public ResponseEntity<ApiKeyResponse> rotateApiKey(@PathVariable UUID merchantId, @PathVariable UUID keyId, @RequestBody ApiKeyCreationRequest apiKeyCreationRequest){
        return ResponseEntity.status(HttpStatus.OK).body(
                apiKeyService.rotateKey(merchantId,keyId,apiKeyCreationRequest)
        );
    }

}
