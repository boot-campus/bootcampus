package com.boot.campus.auth.infrastructure;

import com.boot.campus.member.domain.MemberType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class LoginManagerComposite {
    
    private final Map<MemberType, LoginManger> loginMangers;
    
    public LoginManagerComposite(final Set<LoginManger> loginMangers) {
        this.loginMangers = loginMangers.stream()
                                        .collect(toMap(LoginManger::getLoginType,
                                                identity()));
    }
    
    public LoginManger getLoginManager(MemberType memberType) {
        return loginMangers.get(memberType);
    }
}
