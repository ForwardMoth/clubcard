package com.example.clubcard.service;

public interface JwtService {
    boolean isAccess(Long id, String auth);
}
