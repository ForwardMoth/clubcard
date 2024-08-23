package com.example.clubcard.service.impl;

import com.example.clubcard.domain.dto.request.privilege.PrivilegeRequest;
import com.example.clubcard.domain.dto.request.user.UserUpdateRequest;
import com.example.clubcard.domain.dto.response.user.UserBalanceResponse;
import com.example.clubcard.domain.dto.response.user.UserProfileResponse;
import com.example.clubcard.domain.dto.response.user.UserResponse;
import com.example.clubcard.domain.dto.response.user.UserStatusResponse;
import com.example.clubcard.domain.entity.Privilege;
import com.example.clubcard.domain.entity.User;
import com.example.clubcard.domain.enums.PrivilegeEnum;
import com.example.clubcard.domain.enums.RoleEnum;
import com.example.clubcard.domain.mapper.UserMapper;
import com.example.clubcard.exception.CustomException;
import com.example.clubcard.exception.message.UserErrorMessage;
import com.example.clubcard.repository.RoleRepository;
import com.example.clubcard.repository.UserRepository;
import com.example.clubcard.service.PrivilegeService;
import com.example.clubcard.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeService privilegeService;
    private final UserMapper userMapper;

    public User create(User user){
        user.setIsBlocked(false);
        user.setMoney(0);
        user.setRole(roleRepository.findByName(RoleEnum.USER.name()).orElseThrow(
                () -> new CustomException(UserErrorMessage.ROLE_NOT_FOUND.getDescription(), HttpStatus.NOT_FOUND)
        ));
        user.setPrivilege(privilegeService.getByName(PrivilegeEnum.STANDARD.getName()));
        return userRepository.save(user);
    }

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Boolean isExisted(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User with '%s' isn't found", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getAuthorities()
        );
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new CustomException(UserErrorMessage.USER_IS_NOT_FOUND.getDescription(), HttpStatus.NOT_FOUND)
        );
    }

    public UserProfileResponse getProfile(Long id){
        return userMapper.toProfileResponse(findById(id));
    }

    public UserBalanceResponse getBalance(Long id){
        return userMapper.toBalanceResponce(findById(id));
    }

    public UserStatusResponse getStatus(Long id){
        return userMapper.toStatusResponse(findById(id));
    }

    public UserResponse getUser(Long id){
        return userMapper.toDto(findById(id));
    }

    public UserResponse updateProfile(Long id, UserUpdateRequest request){
        User user = findById(id);
        userMapper.updateUserFromDto(request, user);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserStatusResponse updateStatus(Long id){
        User user = findById(id);
        user.setIsBlocked(!user.getIsBlocked());
        userRepository.save(user);
        return userMapper.toStatusResponse(user);
    }

    public UserResponse updatePrivilege(Long id, PrivilegeRequest request){
        Privilege privilege = privilegeService.findById(request.getPrivilegeId());
        User user = findById(id);
        user.setPrivilege(privilege);
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}

