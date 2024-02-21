package com.boot.campus.auth.application;

import com.boot.campus.auth.application.dto.LoginCommand;
import com.boot.campus.auth.infrastructure.LoginManagerComposite;
import com.boot.campus.auth.infrastructure.LoginManger;
import com.boot.campus.member.domain.Member;
import com.boot.campus.member.persistence.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AuthService {
    
    private final LoginManagerComposite loginManagerComposite;
    
    private final MemberRepository memberRepository;
    
    public AuthService(final LoginManagerComposite loginManagerComposite,
                       final MemberRepository memberRepository) {
        this.loginManagerComposite = loginManagerComposite;
        this.memberRepository = memberRepository;
    }
    
    public Long login(final LoginCommand command) {
        final LoginManger loginManger = loginManagerComposite.getLoginManager(command.memberType());
        final Member member = loginManger.login(command.code());
        final Member registerdMember = memberRepository.findByMemberIdentity(member.getMemberIdentity())
                                                       .orElseGet(() -> register(member));
        return registerdMember.getId();
    }
    
    private Member register(final Member member) {
        return memberRepository.save(member);
    }
}

