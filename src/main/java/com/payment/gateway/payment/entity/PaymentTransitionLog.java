package com.payment.gateway.payment.entity;

import com.payment.gateway.common.enums.PaymentActor;
import com.payment.gateway.common.enums.PaymentEvent;
import com.payment.gateway.common.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class PaymentTransitionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "payment_id",nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus toStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentEvent paymentEvent;

    // Most of the time is system
    // who made this action happen, but in some cases, it can be a user or an external system
    @Enumerated(EnumType.STRING)
    private PaymentActor actor;

    @Column(nullable = false)
    private LocalDateTime occurredAt;

}
