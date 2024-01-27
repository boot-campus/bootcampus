package com.boot.campus.auth.exception;

import com.boot.campus.common.exception.BaseException;
import com.boot.campus.common.exception.BaseExceptionType;

public class AuthException extends BaseException {
    
    private final AuthExceptionType authExceptionType;
    
    public AuthException(final AuthExceptionType authExceptionType) {
        this.authExceptionType = authExceptionType;
    }
    
    @Override
    public BaseExceptionType baseExceptionType() {
        return authExceptionType;
    }
}
