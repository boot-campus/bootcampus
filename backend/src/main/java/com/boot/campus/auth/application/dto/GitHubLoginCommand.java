package com.boot.campus.auth.application.dto;

public record GitHubLoginCommand(String code) {

    public static GitHubLoginCommand from(final String code) {
        return new GitHubLoginCommand(code);
    }
}
