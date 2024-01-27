package com.boot.campus.auth.infrastructure.github;

import com.boot.campus.auth.infrastructure.LoginManger;
import com.boot.campus.auth.infrastructure.github.dto.GitHubMember;
import com.boot.campus.auth.infrastructure.github.dto.GitHubToken;
import com.boot.campus.member.domain.Member;
import com.boot.campus.member.domain.MemberType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.boot.campus.member.domain.MemberType.GITHUB;

@Component
public class GitHubManager implements LoginManger {
    
    private final GitHubApiClient gitHubApiClient;
    
    private final GitHubConfig gitHubConfig;
    
    public GitHubManager(final GitHubApiClient gitHubApiClient,
                         final GitHubConfig gitHubConfig) {
        this.gitHubApiClient = gitHubApiClient;
        this.gitHubConfig = gitHubConfig;
    }
    
    @Override
    public MemberType getLoginType() {
        return GITHUB;
    }
    
    @Override
    public Member login(final String code) {
        try {
            final GitHubToken token = gitHubApiClient.getToken(requestParams(code));
            final GitHubMember gitHubMember = gitHubApiClient.getMember("Bearer " + token.accessToken());
            return gitHubMember.toMember();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("인증 코드가 올바르지 않습니다");
        }
    }
    
    private Map<String, String> requestParams(String code) {
        final Map<String, String> params = new LinkedHashMap<>();
        params.put("client_id", gitHubConfig.clientId());
        params.put("client_secret", gitHubConfig.clientSecret());
        params.put("code", code);
        return params;
    }
    
}
