package com.boot.campus.member.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

@Embeddable
public class MemberIdentity {
    
    private String uniqueId;
    
    @Enumerated(EnumType.STRING)
    private MemberType memberType;
    
    protected MemberIdentity() {
    }
    
    public MemberIdentity(final String uniqueId,
                          final MemberType memberType) {
        this.uniqueId = uniqueId;
        this.memberType = memberType;
    }
    
    public String getUniqueId() {
        return uniqueId;
    }
    
    public MemberType getMemberType() {
        return memberType;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MemberIdentity that = (MemberIdentity) o;
        return Objects.equals(uniqueId, that.uniqueId) && memberType == that.memberType;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, memberType);
    }
}
