package com.payment.gateway.merchant.controller;

import com.payment.gateway.merchant.dto.request.MerchantSignupRequest;
import com.payment.gateway.merchant.dto.response.MerchantResponse;
import com.payment.gateway.merchant.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MerchantResponse> auth(@RequestBody @Valid MerchantSignupRequest merchantSignupRequest) {
        log.info("Signup request received");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authService.signup(merchantSignupRequest)
        );
    }

}
