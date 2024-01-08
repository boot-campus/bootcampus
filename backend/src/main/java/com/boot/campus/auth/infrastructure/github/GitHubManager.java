package com.boot.campus.auth.infrastructure.github;

import com.boot.campus.auth.infrastructure.github.dto.GitHubMember;
import com.boot.campus.auth.infrastructure.github.dto.GitHubToken;
import com.boot.campus.member.domain.Member;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

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
        final Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", gitHubConfig.clientId());
        params.put("client_secret", gitHubConfig.clientSecret());
        final GitHubToken token = gitHubApiClient.getToken(params);
        final GitHubMember gitHubMember = gitHubApiClient.getMember(token.accessToken());
        return gitHubMember.toMember();
    }
}
