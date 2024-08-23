package com.example.clubcard.service;

import com.example.clubcard.domain.dto.request.SignInRequest;
import com.example.clubcard.domain.dto.request.SignUpRequest;
import com.example.clubcard.domain.dto.response.jwt.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse signUp(SignUpRequest request);
    JwtAuthResponse signIn(SignInRequest request);
}
