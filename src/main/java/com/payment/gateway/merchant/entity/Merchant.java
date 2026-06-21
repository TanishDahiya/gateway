package com.payment.gateway.merchant.entity;

import com.payment.gateway.common.enums.BusinessType;
import com.payment.gateway.common.enums.MerchantStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "merchant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false,length = 200)
    private String name;
    @Column(unique = true, nullable = false, length = 200)
    private String email;
    @Column(length = 12)
    private String contactNumber;
    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;
    @Column(length = 200)
    private String businessName;
    @Column(length = 200)
    private String websiteUrl;
    @Column(length = 50,nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MerchantStatus status =  MerchantStatus.PENDING_KYC;
    @Column(length = 50)
    private String gstId;
    @Column(length = 50)
    private String settlementBankAccount;
    @Column(length = 50)
    private String settlementBankIfsc;
    @Column(length = 50)
    private String settlementBankAccountHolderName;

}
