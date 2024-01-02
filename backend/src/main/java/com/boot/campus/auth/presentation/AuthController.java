package com.boot.campus.auth.presentation;

import com.boot.campus.auth.application.dto.GitHubLoginCommand;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Void> githubLogin(@RequestParam final String code,
                                            final HttpSession httpSession) {
        final GitHubLoginCommand command = GitHubLoginCommand.from(code);
        return null;
    }
}
