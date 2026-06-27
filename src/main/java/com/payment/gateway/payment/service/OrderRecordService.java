package com.payment.gateway.payment.service;

import com.payment.gateway.payment.dto.request.OrderRecordRequest;
import com.payment.gateway.payment.dto.response.OrderRecordResponse;
import com.payment.gateway.payment.dto.response.PaymentResponse;
import java.util.List;
import java.util.UUID;

public interface OrderRecordService {
    OrderRecordResponse create(UUID merchantId, OrderRecordRequest orderRecordRequest);

    OrderRecordResponse getById(UUID merchantId, UUID orderId);

    OrderRecordResponse cancelOrder(UUID orderId,UUID merchantId);

    List<PaymentResponse> listPayements(UUID orderId,UUID merchantId);
}
