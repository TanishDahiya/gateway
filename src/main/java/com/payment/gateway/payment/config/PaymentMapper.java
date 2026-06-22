package com.payment.gateway.payment.config;


import com.payment.gateway.payment.dto.response.OrderRecordResponse;
import com.payment.gateway.payment.entity.OrderRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    OrderRecordResponse toOrderRecordResponse(OrderRecord orderRecord);
}
