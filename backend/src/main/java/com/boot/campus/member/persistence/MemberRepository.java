package com.boot.campus.member.persistence;

import com.boot.campus.member.domain.Member;
import com.boot.campus.member.domain.MemberIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
    Optional<Member> findByMemberIdentity(final MemberIdentity memberIdentity);
}
