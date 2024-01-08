package com.boot.campus.auth.infrastructure.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record GitHubToken(String accessToken,
                          String scope,
                          String tokenType) {
}
