package com.payment.gateway.payment.controller;

import com.payment.gateway.payment.dto.request.OrderRecordRequest;
import com.payment.gateway.payment.dto.response.OrderRecordResponse;
import com.payment.gateway.payment.service.OrderRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
