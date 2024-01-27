package com.boot.campus.auth.application;

import com.boot.campus.auth.application.dto.LoginCommand;
import com.boot.campus.auth.exception.AuthException;
import com.boot.campus.auth.infrastructure.github.GitHubApiClient;
import com.boot.campus.auth.infrastructure.github.dto.GitHubMember;
import com.boot.campus.auth.infrastructure.github.dto.GitHubToken;
import com.boot.campus.member.domain.Member;
import com.boot.campus.member.domain.MemberType;
import com.boot.campus.member.persistence.MemberRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpClientErrorException;

import static com.boot.campus.auth.exception.AuthExceptionType.INVALID_OAUTH_API_REQUEST;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Sql("/truncate.sql")
@ActiveProfiles("test")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GitHubAuthServiceTest {
    
    private static final GitHubMember gitHubMember = new GitHubMember("nickname", 1L);
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @MockBean
    private GitHubApiClient gitHubApiClient;
    
    @Test
    void 존재하지_않는_멤버라면_새로_멤버로_등록하고_로그인에_성공한다() {
        //given
        mockGithubApiClient();
        final Member member = gitHubMember.toMember();
        
        //when
        final Long actualMemberId = authService.login(loginCommand());
        final Member savedMember = memberRepository.findByMemberIdentity(member.getMemberIdentity())
                                                   .get();
        final Long expectedMemberId = savedMember.getId();
        
        //then
        SoftAssertions.assertSoftly(softAssertions ->
                softAssertions.assertThat(actualMemberId).isEqualTo(expectedMemberId)
        );
    }
    
    @Test
    void 이미_존재하는_멤버라면_로그인에_성공한다() {
        mockGithubApiClient();
        final Member member = gitHubMember.toMember();
        Member savedMember = memberRepository.save(member);
        
        //when
        final Long actualMemberId = authService.login(loginCommand());
        final Long expectedMemberId = savedMember.getId();
        
        //then
        SoftAssertions.assertSoftly(softAssertions ->
                softAssertions.assertThat(actualMemberId).isEqualTo(expectedMemberId)
        );
    }
    
    @Test
    void 인증코드가_유효하지_않은_경우_로그인에_실패한다() {
        //given
        Mockito.when(gitHubApiClient.getToken(any()))
               .thenThrow(HttpClientErrorException.class);
        final MemberType memberType = MemberType.GITHUB;
        final LoginCommand loginCommand = LoginCommand.from(memberType, "wrongAuthCode");
        
        //when & then
        SoftAssertions.assertSoftly(softAssertions ->
            softAssertions.assertThatThrownBy(() -> authService.login(loginCommand))
                          .usingRecursiveComparison()
                          .isEqualTo(new AuthException(INVALID_OAUTH_API_REQUEST)));
    }
    
    private void mockGithubApiClient() {
        Mockito.when(gitHubApiClient.getToken(any()))
               .thenReturn(new GitHubToken("accessToken", "scope", "tokenType"));
        Mockito.when(gitHubApiClient.getMember(any()))
               .thenReturn(gitHubMember);
    }
    
    private LoginCommand loginCommand() {
        final MemberType memberType = MemberType.GITHUB;
        return LoginCommand.from(memberType, "code");
    }
}
