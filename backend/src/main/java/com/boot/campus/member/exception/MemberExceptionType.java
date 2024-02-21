package com.boot.campus.member.exception;

import com.boot.campus.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum MemberExceptionType implements BaseExceptionType {
    INVALID_MEMBER_TYPE(
            HttpStatus.BAD_REQUEST,
            300,
            "일치하는 멤버 타입이 존재하지 않습니다"),
    ;
    
    private final HttpStatus httpStatus;
    
    private final int errorCode;
    
    private final String errorMessage;
    
    MemberExceptionType(final HttpStatus httpStatus,
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
