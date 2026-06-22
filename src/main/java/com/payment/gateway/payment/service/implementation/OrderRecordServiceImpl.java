package com.payment.gateway.payment.service.implementation;

import com.payment.gateway.common.enums.ErrorCode;
import com.payment.gateway.common.enums.OrderStatus;
import com.payment.gateway.common.exception.DuplicateResourceException;
import com.payment.gateway.payment.config.PaymentMapper;
import com.payment.gateway.payment.dto.request.OrderRecordRequest;
import com.payment.gateway.payment.dto.response.OrderRecordResponse;
import com.payment.gateway.payment.entity.OrderRecord;
import com.payment.gateway.payment.repository.OrderRecordRepository;
import com.payment.gateway.payment.service.OrderRecordService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderRecordServiceImpl implements OrderRecordService {

    private final PaymentMapper paymentMapper;

    @Value("${payment.order.default.expiry.minutes:30}")
    private int defaultExpireMinutes;

    private final OrderRecordRepository orderRecordRepository;

    @Override
    @Transactional
    public OrderRecordResponse create(UUID merchantId, OrderRecordRequest orderRecordRequest) {

        if(orderRecordRequest.receipt() !=null && orderRecordRepository.existsByMerchantIdAndReceipt(merchantId,orderRecordRequest.receipt())){
            throw new DuplicateResourceException(ErrorCode.DUPLICATE_RESOURCE,"Order with Receipt already exists" + orderRecordRequest.receipt());
        }

        OrderRecord orderRecord = OrderRecord.builder()
                .merchantId(merchantId)
                .receipt(orderRecordRequest.receipt())
                .amount(orderRecordRequest.amount())
                .notes(orderRecordRequest.notes())
                .orderStatus(OrderStatus.CREATED)
                .expiresAt(orderRecordRequest.expiresAt()!=null ? orderRecordRequest.expiresAt() : LocalDateTime.now().plusMinutes(defaultExpireMinutes))
                .build();

        orderRecordRepository.save(orderRecord);

        // Later publish to kafka
        return paymentMapper.toOrderRecordResponse(orderRecord);

    }

}
