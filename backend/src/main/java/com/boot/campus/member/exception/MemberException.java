package com.boot.campus.member.exception;

import com.boot.campus.common.exception.BaseException;
import com.boot.campus.common.exception.BaseExceptionType;

public class MemberException extends BaseException {
    
    private final MemberExceptionType memberExceptionType;
    
    public MemberException(final MemberExceptionType memberExceptionType) {
        this.memberExceptionType = memberExceptionType;
    }
    
    @Override
    public BaseExceptionType baseExceptionType() {
        return memberExceptionType;
    }
}
