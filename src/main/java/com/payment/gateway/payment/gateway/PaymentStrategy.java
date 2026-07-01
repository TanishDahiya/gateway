package com.payment.gateway.payment.gateway;

import com.payment.gateway.payment.dto.request.PaymentGatewayRequest;

public interface PaymentStrategy {

    void initiatePayment(PaymentGatewayRequest paymentRequest);
}
