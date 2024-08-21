package com.example.clubcard.service;

import com.example.clubcard.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User create(User user);

    Optional<User> getByEmail(String email);

    Boolean isExisted(String email);
}
