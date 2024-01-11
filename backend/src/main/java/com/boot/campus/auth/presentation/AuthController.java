package com.boot.campus.auth.presentation;

import com.boot.campus.auth.application.AuthService;
import com.boot.campus.auth.application.dto.LoginCommand;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    
    private static final String SESSION_VALUE = "ID";
    
    private final AuthService authService;
    
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<Void> githubLogin(@RequestParam final String code,
                                            final HttpSession httpSession) {
        final LoginCommand command = LoginCommand.from(code);
        final Long memberId = authService.login(command);
        httpSession.setAttribute(SESSION_VALUE, memberId);
        httpSession.getAttribute(SESSION_VALUE);
        return ResponseEntity.ok().build();
    }
}
