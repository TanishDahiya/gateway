package com.payment.gateway.payment.controller;

import com.payment.gateway.payment.dto.request.OrderRecordRequest;
import com.payment.gateway.payment.dto.response.OrderRecordResponse;
import com.payment.gateway.payment.dto.response.PaymentResponse;
import com.payment.gateway.payment.service.OrderRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderRecordController {

    private final OrderRecordService orderRecordService;

    UUID merchantId = UUID.fromString("2b6fa268-3f38-4f73-9f4f-1e27c643cdee");


    @PostMapping
    public ResponseEntity<OrderRecordResponse> createOrderRecord(@RequestBody OrderRecordRequest orderRecordRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                orderRecordService.create(merchantId, orderRecordRequest)
        );
    }

    @GetMapping("/{orderId}/merchant/{merchantId}")
    public ResponseEntity<OrderRecordResponse> getOrderRecord(@PathVariable UUID orderId, @PathVariable UUID merchantId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                orderRecordService.getById(orderId, merchantId)
        );
    }

    @PostMapping("/{orderId}/merchant/{merchantId}")
    public ResponseEntity<OrderRecordResponse> cancelOrder(@PathVariable UUID orderId, @PathVariable UUID merchantId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                orderRecordService.cancelOrder(orderId, merchantId)
        );
    }

    @GetMapping("/{orderId}/merchant/{merchantId}/paymentList")
    public ResponseEntity<List<PaymentResponse>> getListPayment(@PathVariable UUID orderId, @PathVariable UUID merchantId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                orderRecordService.listPayements(orderId,merchantId)
        );
    }


}
