package com.payment.gateway.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(

        LocalDateTime timestamp,

        int status,

        String errorCode,

        String message,

        List<ValidationError> errors,

        String path
) {
}
