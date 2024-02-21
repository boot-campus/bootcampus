package com.boot.campus.common.exception;

public class CommonException extends BaseException{
    
    private final CommonExceptionType commonExceptionType;
    
    public CommonException(final CommonExceptionType commonExceptionType) {
        this.commonExceptionType = commonExceptionType;
    }
    
    @Override
    public BaseExceptionType baseExceptionType() {
        return commonExceptionType;
    }
}
