package com.example.MerchantOnboarding.Exceptions;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
public enum ErrorCode implements ErrorRequest  {
    EMAIL_INVALID(1,"EMAIL_INVALID","Invalid E-MAIL!",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(2,"PASSWORD_INVALID","Invalid Password!",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS(3,"EMAIL_ALREADY_EXISTS","E-Mail already present!",HttpStatus.CONFLICT),
    PHONE_NUMBER_ALREADY_EXISTS(4,"PHONE_NUMBER_ALREADY_EXISTS","Phone Number already present!",HttpStatus.CONFLICT),
    BUSINESS_NAME_ALREADY_EXISTS(5,"BUSINESS_NAME_ALREADY_EXISTS","Business Name already present!",HttpStatus.CONFLICT),
    ALREADY_REPORTED(6,"ALREADY_REPORTED","Data Already Deleted!",HttpStatus.ALREADY_REPORTED),
    BAD_REQUEST(7,"BAD_REQUEST","This is a bad request!",HttpStatus.BAD_REQUEST),
    NOT_FOUND(8,"NOT_FOUND","Data Not Found!",HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(9,"INTERNAL_SERVER_ERROR","Something went wrong internally!",HttpStatus.INTERNAL_SERVER_ERROR),
    STATUS_INACTIVE(10,"STATUS_INACTIVE","Inactive Status!",HttpStatus.BAD_REQUEST);

    private final int errorId;
    private final String errorCode;
    private String errorMessage;
    private final HttpStatus httpStatus;

    @Override
    public int getErrorId() {
        return errorId;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
