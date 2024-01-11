package com.boot.campus.auth.application;

import com.boot.campus.auth.application.dto.LoginCommand;
import com.boot.campus.auth.infrastructure.github.GitHubManager;
import com.boot.campus.member.domain.Member;
import com.boot.campus.member.persistence.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AuthService {
    
    private final GitHubManager gitHubManager;
    private final MemberRepository memberRepository;
    
    public AuthService(final GitHubManager gitHubManager, final MemberRepository memberRepository) {
        this.gitHubManager = gitHubManager;
        this.memberRepository = memberRepository;
    }
    
    public Long login(final LoginCommand command) {
        final Member member = gitHubManager.login(command.code());
        final Member registerdMember = memberRepository.findByMemberIdentity(member.getMemberIdentity())
                                                       .orElseGet(() -> register(member));
        return registerdMember.getId();
    }
    
    private Member register(final Member member) {
        return memberRepository.save(member);
    }
}

