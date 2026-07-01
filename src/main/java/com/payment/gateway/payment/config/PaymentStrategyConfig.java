package com.payment.gateway.payment.config;

import com.payment.gateway.common.enums.PaymentMethod;
import com.payment.gateway.payment.gateway.PaymentStrategy;
import com.payment.gateway.payment.gateway.strategy.CardPaymentStrategy;
import com.payment.gateway.payment.gateway.strategy.NetBankingPaymentStrategy;
import com.payment.gateway.payment.gateway.strategy.UpiPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class PaymentStrategyConfig {

    private final CardPaymentStrategy cardPaymentStrategy;
    private final NetBankingPaymentStrategy netBankingPaymentStrategy;
    private final UpiPaymentStrategy upiPaymentStrategy;

    @Bean
    public Map<PaymentMethod, PaymentStrategy> paymentStrategies(){
        return Map.of(
                PaymentMethod.CARD, cardPaymentStrategy,
                PaymentMethod.NETBANKING, netBankingPaymentStrategy,
                PaymentMethod.UPI, upiPaymentStrategy
        );
    }

}
