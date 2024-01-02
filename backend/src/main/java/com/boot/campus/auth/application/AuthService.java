package com.boot.campus.auth.application;

import com.boot.campus.auth.application.dto.GitHubLoginCommand;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final String URL = "https://github.com/login/oauth/access_token";

    public void githubLogin(final GitHubLoginCommand command) {

    }
}
