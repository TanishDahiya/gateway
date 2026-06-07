package com.payment.gateway.merchant.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length = 20) //  unique = true, if we can do it is not registered to any other merchants
    private String email;
    @Column(nullable = false,length = 20)
    private String contactNumber;
    @Column(nullable = false,length = 20)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id",nullable = false)
    private Merchant merchant;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


}
