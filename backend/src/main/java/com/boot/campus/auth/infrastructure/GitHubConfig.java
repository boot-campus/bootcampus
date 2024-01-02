package com.boot.campus.auth.infrastructure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.github")
public record GitHubConfig(String clientId,
                           String clientSecret) {
}
