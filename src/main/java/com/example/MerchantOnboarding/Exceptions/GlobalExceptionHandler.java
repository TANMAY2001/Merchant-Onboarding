package com.example.MerchantOnboarding.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> HandlingCustomException(CustomException cex){
        ErrorResponse errorResponse = new ErrorResponse(cex.getErrorRequest());
        return new ResponseEntity<>(errorResponse,cex.getErrorRequest().getHttpStatus());
    }
}
