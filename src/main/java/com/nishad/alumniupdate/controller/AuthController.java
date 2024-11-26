package com.nishad.alumniupdate.controller;

import com.nishad.alumniupdate.dto.request.LoginRequest;
import com.nishad.alumniupdate.dto.response.JwtResponse;
import com.nishad.alumniupdate.security.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(JwtResponse.builder()
                .token(token)
                .type("Bearer")
                .email(loginRequest.getEmail())
                .build());
    }
}