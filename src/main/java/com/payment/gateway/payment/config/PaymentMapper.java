package com.payment.gateway.payment.config;


import com.payment.gateway.payment.dto.response.OrderRecordResponse;
import com.payment.gateway.payment.dto.request.PaymentGatewayRequest;
import com.payment.gateway.payment.dto.response.PaymentResponse;
import com.payment.gateway.payment.entity.OrderRecord;
import com.payment.gateway.payment.entity.Payment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    OrderRecordResponse toOrderRecordResponse(OrderRecord orderRecord);

    PaymentResponse toPaymentResponse(Payment payment);

    List<PaymentResponse> toPaymentListResponse(List<Payment> payment);

    PaymentGatewayRequest toPaymentGatewayRequest(Payment payment);
}
