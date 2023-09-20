package com.example.MerchantOnboarding.Exceptions;
import lombok.Data;


@Data
public class ErrorResponse {
    private int errorId;
    private String errorCode;
    private String errorMessage;


    public ErrorResponse(ErrorRequest errorRequest){
       this.errorId = errorRequest.getErrorId();
       this.errorCode = errorRequest.getErrorCode();
       this.errorMessage = errorRequest.getErrorMessage();
    }
}
