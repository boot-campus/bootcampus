package com.boot.campus.member.domain;

public enum MemberType {
    
    GITHUB,
    ;
    
    public static MemberType fromName(String name) {
        try{
            return MemberType.valueOf(name);
        }catch (IllegalArgumentException e) {
            throw new RuntimeException("일치하는 멤버 타입이 존재하지 않습니다");
        }
    }
}
