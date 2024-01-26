package com.boot.campus.member.domain;

public enum MemberType {
    
    GITHUB,
    ;
    
    public static MemberType fromName(String name) {
        return MemberType.valueOf(name);
    }
}
