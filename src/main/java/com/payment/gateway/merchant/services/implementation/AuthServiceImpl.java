package com.payment.gateway.merchant.services.implementation;

import com.payment.gateway.common.enums.ErrorCode;
import com.payment.gateway.common.enums.MerchantStatus;
import com.payment.gateway.common.enums.UserRole;
import com.payment.gateway.common.exception.DuplicateResourceException;
import com.payment.gateway.merchant.config.MerchantMapper;
import com.payment.gateway.merchant.dto.request.MerchantSignupRequest;
import com.payment.gateway.merchant.dto.response.MerchantResponse;
import com.payment.gateway.merchant.entity.AppUser;
import com.payment.gateway.merchant.entity.Merchant;
import com.payment.gateway.merchant.repository.AppUserRepository;
import com.payment.gateway.merchant.repository.MerchantRepository;
import com.payment.gateway.merchant.services.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;

    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest merchantSignupRequest) {
        if(merchantRepository.existsByEmail(merchantSignupRequest.email())){
            throw new DuplicateResourceException(ErrorCode.MERCHANT_ALREADY_EXISTS,
                    "Merchant already exists : "+ merchantSignupRequest.email());
        }

        Merchant merchant = Merchant.builder()
                .name(merchantSignupRequest.name())
                .businessName(merchantSignupRequest.businessName())
                .businessType(merchantSignupRequest.businessType())
                .email(merchantSignupRequest.email())
                .status(MerchantStatus.PENDING_KYC)
                .build();
        merchantRepository.save(merchant);

        AppUser appUser = AppUser.builder()
                .email(merchantSignupRequest.email())
                .merchant(merchant)
                .passwordHash(merchantSignupRequest.password()) // To Do encrypt
                .role(UserRole.OWNER)
                .build();

        appUserRepository.save(appUser);

        return merchantMapper.toMerchantResponse(merchant);

    }
}
