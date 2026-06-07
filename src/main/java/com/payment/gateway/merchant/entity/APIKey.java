package com.payment.gateway.merchant.entity;

import com.payment.gateway.common.enums.Environment;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "api_key")
public class APIKey {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false )
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;
    @Column(nullable = false, length = 50,unique = true)
    private String keyId;
    @Column(nullable = false, length = 200)
    private String keySecretHash;
    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Environment environment;
    @Column(nullable = false)
    private boolean enabled = true;

    private LocalDateTime createdAt;
    private LocalDateTime rotatedAt;
    private LocalDateTime gracePeriodExpires;

}
