package com.boot.campus.auth.presentation;

import com.boot.campus.auth.application.AuthService;
import com.boot.campus.auth.application.dto.LoginCommand;
import com.boot.campus.member.domain.MemberType;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    
    private final AuthService authService;
    
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login/{memberType}")
    public ResponseEntity<Void> login(@PathVariable final MemberType memberType,
                                      @RequestParam final String code,
                                      final HttpSession httpSession) {
        final LoginCommand command = LoginCommand.from(memberType, code);
        final Long memberId = authService.login(command);
        httpSession.setAttribute("MEMBER_ID", memberId);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .build();
    }
}