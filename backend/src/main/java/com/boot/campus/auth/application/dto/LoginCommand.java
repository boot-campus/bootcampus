package com.boot.campus.auth.application.dto;

import com.boot.campus.member.domain.MemberType;

public record LoginCommand(MemberType memberType,
                           String code) {
    
    public static LoginCommand from(final MemberType memberType,
                                    final String code) {
        return new LoginCommand(memberType, code);
    }
}
