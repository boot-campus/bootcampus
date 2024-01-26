package com.boot.campus.auth.infrastructure;

import com.boot.campus.member.domain.Member;
import com.boot.campus.member.domain.MemberType;

public interface LoginManger {
    
    MemberType getLoginType();
    
    Member login(String code);
}
