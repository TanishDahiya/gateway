package com.payment.gateway.operations;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class SettlementPayment {

    @EmbeddedId
    private SettlementPaymentId settlementPaymentId;

    //NTC
    @MapsId()
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settlement_id")
    private Settlement settlement;

}
