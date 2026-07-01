package com.payment.gateway.payment.gateway;

import com.payment.gateway.common.enums.PaymentMethod;
import com.payment.gateway.payment.dto.request.PaymentGatewayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentGatewayRouter {

    private final Map<PaymentMethod, PaymentStrategy>  paymentStrategys;

    public void initiatePayment(PaymentGatewayRequest paymentRequest) {

        PaymentStrategy paymentStrategy = paymentStrategys.get(paymentRequest.paymentMethod());

        if (paymentStrategy == null) {
            throw new IllegalArgumentException("Unsupported payment method: " + paymentRequest.paymentMethod());
        }

        paymentStrategy.initiatePayment(paymentRequest);
    }
}
