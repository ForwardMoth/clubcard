package com.example.clubcard.service;

import com.example.clubcard.domain.dto.sign.SignInRequest;
import com.example.clubcard.domain.dto.sign.SignUpRequest;
import com.example.clubcard.domain.dto.jwt.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse signUp(SignUpRequest request);
    JwtAuthResponse signIn(SignInRequest request);
}
