package com.payment.gateway.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Embeddable
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Money {

    @Column(name = "amount_units", nullable = false)
    private long amountUnits;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    public Money plus(Money other) {
        validateCurrency(other);
        return new Money(
                this.amountUnits + other.amountUnits,
                this.currency
        );
    }

    public Money minus(Money other) {
        validateCurrency(other);
        return new Money(
                this.amountUnits - other.amountUnits,
                this.currency
        );
    }

    public static Money of(long amountUnits, String currency) {
        return new Money(amountUnits, currency);
    }

    public static Money inr(long amountUnits) {
        return new Money(amountUnits, "INR");
    }

    private void validateCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency mismatch: " + this.currency + " and " + other.currency);
        }
    }
}