package com.example.clubcard.service;

import com.example.clubcard.domain.dto.page.PageDto;
import com.example.clubcard.domain.dto.privilege.PrivilegeIdRequest;
import com.example.clubcard.domain.dto.user.*;
import com.example.clubcard.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(User user);

    User create(User user);

    User findByEmail(String email);

    Boolean isExisted(String email);

    User findById(Long id);

    UserProfileResponse getProfile(Long id);

    UserBalanceResponse getBalance(Long id);

    UserStatusResponse getStatus(Long id);

    UserResponse getUser(Long id);

    UserResponse updateProfile(Long id, UserUpdateRequest request);

    UserStatusResponse updateStatus(Long id);

    UserResponse updatePrivilege(Long id, PrivilegeIdRequest request);

    void deleteUser(Long id);

    Page<UserResponse> getAllUsers(PageDto pageDto, UserFilterRequest userFilterRequest);

    UserQrCodeResponse getQrCode(Long id);

    UserResponse getUserByQrCode(String uuid);

    UserBalanceResponse updateBalance(Long id, UserBalanceRequest request);
}
