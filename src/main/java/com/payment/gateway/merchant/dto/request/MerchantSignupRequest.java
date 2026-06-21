package com.payment.gateway.merchant.dto.request;

import com.payment.gateway.common.enums.BusinessType;
import com.payment.gateway.common.enums.MerchantStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;


import java.util.UUID;

public record MerchantSignupRequest(

        @NotNull(message = "Name should be provided")
        @Size(max = 50, message = "Name should not be more that 50 characters long")
        String name,

        @Email
        @NotNull(message = "Email is required")
        String email,

        @NotNull(message = "Password is required")
        @Size(min = 8, message = "Password should be at least 8 characters")
        String password,

        BusinessType businessType,

        @Size(max=25, message = "Business name should not be more than 25 characters")
        String businessName

){

}
