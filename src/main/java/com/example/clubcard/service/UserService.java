package com.example.clubcard.service;

import com.example.clubcard.domain.dto.response.UserProfileResponse;
import com.example.clubcard.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User create(User user);

    Optional<User> getByEmail(String email);

    Boolean isExisted(String email);

    User findById(Long id);

    UserProfileResponse getProfile(Long id, String auth);
}
