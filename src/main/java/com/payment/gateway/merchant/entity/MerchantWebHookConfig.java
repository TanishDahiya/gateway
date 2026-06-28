package com.payment.gateway.merchant.entity;

import com.payment.gateway.common.enums.UserRole;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(indexes = {
        @Index(name = "idx_webhook_merchant_id",columnList = "merchant_id, enabled")
})
public class MerchantWebHookConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "merchant_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Merchant merchant;

    @Column(nullable = false, length = 100)
    private String targetUrl;

    @Column(nullable = false, length = 100)
    private String webHookSecretHash;

    @Column(nullable = false, length = 100)
    private Boolean enabled = true;

    @Column(length = 255)
    private String eventTypes; //comma seperated event list
}
