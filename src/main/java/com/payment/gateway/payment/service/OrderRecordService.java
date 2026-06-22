package com.payment.gateway.payment.service;

import com.payment.gateway.payment.dto.request.OrderRecordRequest;
import com.payment.gateway.payment.dto.response.OrderRecordResponse;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public interface OrderRecordService {
    OrderRecordResponse create(UUID merchantId, OrderRecordRequest orderRecordRequest);
}
