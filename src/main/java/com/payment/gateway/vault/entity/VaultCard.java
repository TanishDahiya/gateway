package com.payment.gateway.vault.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class VaultCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String lastFourDigits;

    @Column(nullable = false, length = 100)
    private String bin;

    @Column(nullable = false)
    private byte[] encryptedDek;

    @Column(nullable = false)
    private byte[] panNumber;

    @Column(nullable = false, length = 100)
    private String companyCardName;

    @Column(length = 2)
    private String expiryMonth;

    @Column(length = 4)
    private String expiryYear;

    @Column(length = 100)
    private String cardHolderName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
