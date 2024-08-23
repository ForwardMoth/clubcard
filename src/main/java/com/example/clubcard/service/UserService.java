package com.example.clubcard.service;

import com.example.clubcard.domain.dto.request.privilege.PrivilegeRequest;
import com.example.clubcard.domain.dto.request.user.UserUpdateRequest;
import com.example.clubcard.domain.dto.response.user.UserBalanceResponse;
import com.example.clubcard.domain.dto.response.user.UserProfileResponse;
import com.example.clubcard.domain.dto.response.user.UserResponse;
import com.example.clubcard.domain.dto.response.user.UserStatusResponse;
import com.example.clubcard.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User create(User user);

    Optional<User> getByEmail(String email);

    Boolean isExisted(String email);

    User findById(Long id);

    UserProfileResponse getProfile(Long id);

    UserBalanceResponse getBalance(Long id);

    UserStatusResponse getStatus(Long id);

    UserResponse getUser(Long id);

    UserResponse updateProfile(Long id, UserUpdateRequest request);

    UserStatusResponse updateStatus(Long id);

    UserResponse updatePrivilege(Long id, PrivilegeRequest request);
}
