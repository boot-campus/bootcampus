package com.boot.campus.common.exception;

import com.boot.campus.common.exception.dto.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionResponse> handleBaseException(final BaseException e) {
        final ExceptionResponse response = ExceptionResponse.from(e);

        log.info("base exception error, {}", response);

        return ResponseEntity.status(e.baseExceptionType().httpStatus())
                             .body(response);
    }
    
    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ExceptionResponse> handleTypeMismatchException(final Exception e){
        final CommonExceptionType commonExceptionType = CommonExceptionType.INVALID_REQUEST;
        final ExceptionResponse response = new ExceptionResponse(commonExceptionType.errorCode(), commonExceptionType.errorMessage());
        
        log.info("mismatch exception error, {}", response);
        
        return ResponseEntity.status(commonExceptionType.httpStatus())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(final Exception e) {
        final ExceptionResponse response = new ExceptionResponse(9999, "예상하지 못한 서버 오류입니다. 서버 관리자에게 문의해주세요.");

        log.error("internal server error, {}", e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(response);
    }
}
