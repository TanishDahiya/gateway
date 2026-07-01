package com.payment.gateway.payment.gateway.strategy;

import com.payment.gateway.payment.dto.request.PaymentGatewayRequest;
import com.payment.gateway.payment.gateway.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class NetBankingPaymentStrategy implements PaymentStrategy {
    @Override
    public void initiatePayment(PaymentGatewayRequest paymentRequest) {

    }
}
