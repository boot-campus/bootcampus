package com.boot.campus.member.domain;

import com.boot.campus.member.exception.MemberException;

import static com.boot.campus.member.exception.MemberExceptionType.INVALID_MEMBER_TYPE;

public enum MemberType {
    
    GITHUB,
    ;
    
    public static MemberType fromName(String name) {
        try{
            return MemberType.valueOf(name);
        }catch (IllegalArgumentException e) {
            throw new MemberException(INVALID_MEMBER_TYPE);
        }
    }
}
