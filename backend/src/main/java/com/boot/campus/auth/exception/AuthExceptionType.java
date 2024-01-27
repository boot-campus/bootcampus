package com.boot.campus.auth.exception;

import com.boot.campus.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum AuthExceptionType implements BaseExceptionType {
    
    INVALID_OAUTH_API_REQUEST(
            HttpStatus.BAD_REQUEST,
            200,
            "OAuth 서버에 올바르지 않은 요청을 보냈습니다."
    )
    ;
    
    private final HttpStatus httpStatus;
    
    private final int errorCode;
    
    private final String errorMessage;
    
    AuthExceptionType(final HttpStatus httpStatus,
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
