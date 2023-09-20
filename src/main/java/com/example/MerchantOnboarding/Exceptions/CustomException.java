package com.example.MerchantOnboarding.Exceptions;

public class CustomException extends RuntimeException{
    private final transient ErrorRequest errorRequest;

    public CustomException(ErrorRequest errorRequest){
        super(errorRequest.getErrorMessage());
        this.errorRequest=errorRequest;
    }

    public ErrorRequest getErrorRequest() {
        return this.errorRequest;
    }
}
