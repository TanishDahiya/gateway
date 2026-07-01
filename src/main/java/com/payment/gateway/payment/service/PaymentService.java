package com.payment.gateway.payment.service;

import com.payment.gateway.payment.dto.response.PaymentResponse;
import com.payment.gateway.payment.gateway.dto.PaymentRequest;

import java.util.UUID;

public interface PaymentService {

    PaymentResponse initiatePayment(UUID merchantId, PaymentRequest paymentRequest);

}
