package com.boot.campus.auth.infrastructure.github;

import com.boot.campus.auth.infrastructure.github.dto.GitHubMember;
import com.boot.campus.auth.infrastructure.github.dto.GitHubToken;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface GitHubApiClient {
    
    @PostExchange(url = "https://github.com/login/oauth/access_token", accept = MediaType.APPLICATION_JSON_VALUE)
    GitHubToken getToken(@RequestParam final Map<String, String> params);
    
    @GetExchange(url = "https://api.github.com/user")
    GitHubMember getMember(@RequestHeader(name = HttpHeaders.AUTHORIZATION) final String bearerToken);
}
