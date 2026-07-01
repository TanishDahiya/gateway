package com.payment.gateway.payment.gateway.strategy;

import com.payment.gateway.payment.dto.request.PaymentGatewayRequest;
import com.payment.gateway.payment.gateway.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public void initiatePayment(PaymentGatewayRequest paymentRequest) {
        // Implement card payment initiation logic here
    }
}
