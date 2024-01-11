package com.boot.campus.auth.application.dto;

public record LoginCommand(String code) {
    
    public static LoginCommand from(final String code) {
        return new LoginCommand(code);
    }
}
