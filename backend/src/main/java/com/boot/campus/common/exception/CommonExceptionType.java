package com.boot.campus.common.exception;

import org.springframework.http.HttpStatus;

public enum CommonExceptionType implements BaseExceptionType {
    
    INVALID_REQUEST(
            HttpStatus.BAD_REQUEST,
            100,
            "요청 값이 잘못되었습니다."
    ),
    ;
    
    private final HttpStatus httpStatus;
    
    private final int errorCode;
    
    private final String errorMessage;
    
    CommonExceptionType(final HttpStatus httpStatus,
                               final int errorCode,
                               final String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }
    
    @Override
    public int errorCode() {
        return errorCode;
    }
    
    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
