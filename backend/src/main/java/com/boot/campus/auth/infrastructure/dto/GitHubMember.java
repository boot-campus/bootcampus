package com.boot.campus.auth.infrastructure.dto;

import com.boot.campus.member.domain.Member;
import com.boot.campus.member.domain.MemberIdentity;
import com.boot.campus.member.domain.MemberType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubMember(String login,
                           Long id) {

    public Member toMember() {
        final MemberIdentity memberIdentity = new MemberIdentity(String.valueOf(id),
                                                                 MemberType.GITHUB);
        return new Member(memberIdentity, login);
    }
}
