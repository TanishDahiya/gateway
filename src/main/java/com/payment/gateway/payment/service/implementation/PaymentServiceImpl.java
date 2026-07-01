package com.payment.gateway.payment.service.implementation;

import com.payment.gateway.common.enums.ErrorCode;
import com.payment.gateway.common.enums.OrderStatus;
import com.payment.gateway.common.enums.PaymentStatus;
import com.payment.gateway.common.exception.BuisnessRuleViolationException;
import com.payment.gateway.common.exception.ResourceNotFoundException;
import com.payment.gateway.payment.config.PaymentMapper;
import com.payment.gateway.payment.dto.request.PaymentGatewayRequest;
import com.payment.gateway.payment.gateway.dto.PaymentRequest;
import com.payment.gateway.payment.dto.response.PaymentResponse;
import com.payment.gateway.payment.entity.OrderRecord;
import com.payment.gateway.payment.entity.Payment;
import com.payment.gateway.payment.gateway.PaymentGatewayRouter;
import com.payment.gateway.payment.repository.OrderRecordRepository;
import com.payment.gateway.payment.repository.PaymentRepository;
import com.payment.gateway.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final OrderRecordRepository orderRecordRepository;

    private final PaymentRepository paymentRepository;

    private final PaymentGatewayRouter paymentGatewayRouter;

    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponse initiatePayment(UUID merchantId, PaymentRequest paymentRequest) {

        OrderRecord orderRecord = orderRecordRepository.findByIdAndMerchantId(paymentRequest.orderId(), merchantId)
                .orElseThrow(()-> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "Order Record not found"));


        if(orderRecord.getOrderStatus() != OrderStatus.ATTEMPTED && orderRecord.getOrderStatus() != OrderStatus.CREATED ) {
            throw new BuisnessRuleViolationException(ErrorCode.FORBIDDEN, "Oder not Payable, because it's status is : "+orderRecord.getOrderStatus());
        }

        orderRecord.setOrderStatus(OrderStatus.ATTEMPTED);
        orderRecord.setAttempts(orderRecord.getAttempts() + 1);

        Payment payment = Payment.builder()
                .orderRecord(orderRecord)
                .merchantId(merchantId)
                .amount(orderRecord.getAmount())
                .paymentStatus(PaymentStatus.CREATED)
                .paymentMethod(paymentRequest.paymentMethod())
                .methodDetails(paymentRequest.methodDetails())
                .build();

        paymentRepository.save(payment);

        PaymentGatewayRequest paymentGatewayResponse = paymentMapper.toPaymentGatewayRequest(payment);

        paymentGatewayRouter.initiatePayment(paymentGatewayResponse);

        return paymentMapper.toPaymentResponse(payment);

    }

}
