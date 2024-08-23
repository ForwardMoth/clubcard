package com.example.clubcard.controller;

import com.example.clubcard.controller.api.AuthApi;
import com.example.clubcard.domain.dto.request.SignInRequest;
import com.example.clubcard.domain.dto.request.SignUpRequest;
import com.example.clubcard.domain.dto.response.jwt.JwtAuthResponse;
import com.example.clubcard.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponse> signUp(@RequestBody @Valid SignUpRequest request){
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody @Valid SignInRequest request){
        return ResponseEntity.ok(authService.signIn(request));
    }
}
