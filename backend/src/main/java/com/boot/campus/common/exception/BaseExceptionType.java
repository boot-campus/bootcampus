package com.boot.campus.common.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {

    HttpStatus httpStatus();

    int errorCode();

    String errorMessage();
}
