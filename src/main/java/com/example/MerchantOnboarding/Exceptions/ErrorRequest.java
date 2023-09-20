package com.example.MerchantOnboarding.Exceptions;
import org.springframework.http.HttpStatus;

public interface ErrorRequest {
    int getErrorId();

    String getErrorCode();

    String getErrorMessage();

    HttpStatus getHttpStatus();

    void setErrorMessage(String errorMessage);
}
