package com.example.clubcard.service.impl;

import com.example.clubcard.domain.entity.User;
import com.example.clubcard.jwt.JwtAuthFilter;
import com.example.clubcard.jwt.JwtCore;
import com.example.clubcard.repository.UserRepository;
import com.example.clubcard.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtAuthFilter jwtAuthFilter;
    private final JwtCore jwtCore;
    private final UserRepository userRepository;

    public boolean isAccess(Long id, String auth){
        String token = jwtAuthFilter.getToken(auth);
        String email = jwtCore.extractEmail(token);
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with '%s' isn't found", email))
        );
        return Objects.equals(user.getId(), id);
    }
}
