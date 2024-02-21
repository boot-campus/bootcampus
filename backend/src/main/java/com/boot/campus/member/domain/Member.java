package com.boot.campus.member.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    private MemberIdentity memberIdentity;
    
    private String nickname;
    
    protected Member() {
    }
    
    public Member(final MemberIdentity memberIdentity,
                  final String nickname) {
        this(null, memberIdentity, nickname);
    }
    
    public Member(final Long id,
                  final MemberIdentity memberIdentity,
                  final String nickname) {
        this.id = id;
        this.memberIdentity = memberIdentity;
        this.nickname = nickname;
    }
    
    public Long getId() {
        return id;
    }
    
    public MemberIdentity getMemberIdentity() {
        return memberIdentity;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Member member = (Member) o;
        return Objects.equals(id, member.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
