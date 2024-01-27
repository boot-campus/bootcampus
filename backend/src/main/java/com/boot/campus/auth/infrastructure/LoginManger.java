package com.boot.campus.auth.infrastructure;

import com.boot.campus.auth.exception.AuthException;
import com.boot.campus.member.domain.Member;
import com.boot.campus.member.domain.MemberType;
import org.springframework.web.client.HttpClientErrorException;

import static com.boot.campus.auth.exception.AuthExceptionType.INVALID_OAUTH_API_REQUEST;

public abstract class LoginManger {
    
    public abstract MemberType getLoginType();
    
    protected abstract Member loginByLoginType(String code);
    
    public Member login(String code){
        try {
            return loginByLoginType(code);
        }catch (HttpClientErrorException e) {
            throw new AuthException(INVALID_OAUTH_API_REQUEST);
        }
    }
}
