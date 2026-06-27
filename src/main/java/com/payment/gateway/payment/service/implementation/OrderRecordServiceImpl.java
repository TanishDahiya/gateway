package com.payment.gateway.payment.service.implementation;

import com.payment.gateway.common.enums.ErrorCode;
import com.payment.gateway.common.enums.OrderStatus;
import com.payment.gateway.common.exception.BuisnessRuleViolationException;
import com.payment.gateway.common.exception.DuplicateResourceException;
import com.payment.gateway.common.exception.ResourceNotFoundException;
import com.payment.gateway.payment.config.PaymentMapper;
import com.payment.gateway.payment.dto.request.OrderRecordRequest;
import com.payment.gateway.payment.dto.response.OrderRecordResponse;
import com.payment.gateway.payment.dto.response.PaymentResponse;
import com.payment.gateway.payment.entity.OrderRecord;
import com.payment.gateway.payment.entity.Payment;
import com.payment.gateway.payment.repository.OrderRecordRepository;
import com.payment.gateway.payment.repository.PaymentRepository;
import com.payment.gateway.payment.service.OrderRecordService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderRecordServiceImpl implements OrderRecordService {

    private final PaymentMapper paymentMapper;

    @Value("${payment.order.default.expiry.minutes:30}")
    private int defaultExpireMinutes;

    private final OrderRecordRepository orderRecordRepository;

    private final PaymentRepository paymentRepository;

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

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public OrderRecordResponse getById(UUID orderId,UUID merchantId) {

        OrderRecord order = orderRecordRepository.findByIdAndMerchantId(orderId,merchantId)
                .orElseThrow(()-> new ResourceNotFoundException(ErrorCode.ORDER_NOT_FOUND,"Order Not Found with Merchant Id : {} and Order Id "+ merchantId + orderId));

        return paymentMapper.toOrderRecordResponse(order);
    }

    @Override
    @Transactional
    public OrderRecordResponse cancelOrder(UUID merchantId, UUID orderId) {

        OrderRecord order = orderRecordRepository.findByIdAndMerchantId(orderId,merchantId)
                .orElseThrow(()-> new ResourceNotFoundException(ErrorCode.ORDER_NOT_FOUND,"Order Not Found with Merchant Id : {} and Order Id "+ merchantId + orderId));

        if(order.getOrderStatus() ==  OrderStatus.CANCELED || order.getOrderStatus() ==  OrderStatus.PAID ) {
            throw new BuisnessRuleViolationException(ErrorCode.FORBIDDEN,"Order already cancelled OR paid");
        }

        order.setOrderStatus(OrderStatus.CANCELED);
//        orderRecordRepository.save(order);  // should not be explicitly used because we are using transactional that can perform dirty check
        return paymentMapper.toOrderRecordResponse(order);
    }

    @Override
    public List<PaymentResponse> listPayements(UUID orderId, UUID merchantId) {

        OrderRecord order = orderRecordRepository.findByIdAndMerchantId(orderId,merchantId)
                .orElseThrow(()-> new ResourceNotFoundException(ErrorCode.ORDER_NOT_FOUND,"Order Not Found with Merchant Id : {} and Order Id "+ merchantId + orderId));

        List<Payment> payment = paymentRepository.findByOrderRecord_Id(orderId,merchantId);

        return paymentMapper.toPaymentListResponse(payment);
    }

}
