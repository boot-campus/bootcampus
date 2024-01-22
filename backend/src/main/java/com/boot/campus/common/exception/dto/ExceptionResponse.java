package com.boot.campus.common.exception.dto;

import com.boot.campus.common.exception.BaseException;
import com.boot.campus.common.exception.BaseExceptionType;

public record ExceptionResponse(int errorCode,
                                String errorMessage) {

    public static ExceptionResponse from(final BaseException e) {
        final BaseExceptionType type = e.baseExceptionType();
        return new ExceptionResponse(type.errorCode(), type.errorMessage());
    }
}
