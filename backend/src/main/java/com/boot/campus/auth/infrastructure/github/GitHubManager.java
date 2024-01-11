package com.boot.campus.auth.infrastructure.github;

import com.boot.campus.auth.infrastructure.github.dto.GitHubMember;
import com.boot.campus.auth.infrastructure.github.dto.GitHubToken;
import com.boot.campus.member.domain.Member;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GitHubManager {

    private final GitHubApiClient gitHubApiClient;

    private final GitHubConfig gitHubConfig;

    public GitHubManager(final GitHubApiClient gitHubApiClient,
                         final GitHubConfig gitHubConfig) {
        this.gitHubApiClient = gitHubApiClient;
        this.gitHubConfig = gitHubConfig;
    }

    public Member login(final String code) {
        final Map<String, String> params = new LinkedHashMap<>();
        params.put("client_id", gitHubConfig.clientId());
        params.put("client_secret", gitHubConfig.clientSecret());
        params.put("code", code);
        final GitHubToken token = gitHubApiClient.getToken(params);
        final GitHubMember gitHubMember = gitHubApiClient.getMember("Bearer "+token.accessToken());
        return gitHubMember.toMember();
    }
}
