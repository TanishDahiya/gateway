package com.payment.gateway.operations;

import com.payment.gateway.common.entity.AuditEntitySuper;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class SettlementPayment extends AuditEntitySuper {

    @EmbeddedId
    private SettlementPaymentId settlementPaymentId;

    //NTC
    @MapsId("settlementId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settlement_id")
    private Settlement settlement;

}
