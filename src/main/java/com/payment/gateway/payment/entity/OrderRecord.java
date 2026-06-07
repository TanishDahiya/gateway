package com.payment.gateway.payment.entity;

import com.payment.gateway.common.entity.Money;
import com.payment.gateway.common.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "order_record")
public class OrderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    // This acts as a FK, but we can not add Join Column
    // This is because, following domain level development
    // we can basically denormalize it ( means cross-service boundary )
    // NTC
    @Column(name = "merchant_id",nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Column(nullable = false)
    private Integer attempts=0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus orderStatus;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", length = 1000)
    private Map<String, Object> notes;

    @Column(nullable = false)
    private LocalDateTime expiresAt;


}
