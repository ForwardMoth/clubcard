package com.example.clubcard.service;

import com.example.clubcard.domain.dto.jwt.JwtAuthResponse;
import com.example.clubcard.domain.dto.jwt.JwtRefreshTokenRequest;
import com.example.clubcard.domain.dto.sign.SignInRequest;
import com.example.clubcard.domain.dto.sign.SignUpRequest;

public interface AuthService {
    JwtAuthResponse signUp(SignUpRequest request);

    JwtAuthResponse signIn(SignInRequest request);

    JwtAuthResponse refresh(JwtRefreshTokenRequest request);
}
