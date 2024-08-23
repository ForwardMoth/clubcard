package com.example.clubcard.service.impl;

import com.example.clubcard.domain.dto.response.user.UserBalanceResponse;
import com.example.clubcard.domain.dto.response.user.UserProfileResponse;
import com.example.clubcard.domain.entity.User;
import com.example.clubcard.domain.enums.PrivilegeEnum;
import com.example.clubcard.domain.enums.RoleEnum;
import com.example.clubcard.domain.mapper.UserMapper;
import com.example.clubcard.exception.CustomException;
import com.example.clubcard.exception.message.UserErrorMessage;
import com.example.clubcard.repository.PrivilegeRepository;
import com.example.clubcard.repository.RoleRepository;
import com.example.clubcard.repository.UserRepository;
import com.example.clubcard.service.JwtService;
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
    private final PrivilegeRepository privilegeRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public User create(User user){
        user.setIsBlocked(false);
        user.setMoney(0);
        user.setRole(roleRepository.findByName(RoleEnum.USER.name()).orElseThrow(
                () -> new CustomException(UserErrorMessage.ROLE_NOT_FOUND.getDescription(), HttpStatus.NOT_FOUND)
        ));
        user.setPrivilege(privilegeRepository.findByName(PrivilegeEnum.STANDARD.getName()).orElseThrow(
                () -> new CustomException(UserErrorMessage.PRIVILEGE_NOT_FOUND.getDescription(), HttpStatus.NOT_FOUND)
        ));
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
        User user = findById(id);
        return userMapper.toProfileResponse(user);
    }

    public UserBalanceResponse getBalance(Long id){
        User user = findById(id);
        return new UserBalanceResponse(user.getMoney());
    }

//    public User getUser(Long id, String auth){
//        if (!jwtService.isAccess(id, auth)){
//            throw new CustomException(AuthErrorMessage.NO_ACCESS.getMsg(), AuthErrorMessage.NO_ACCESS.getStatus());
//        }
//        User user = findById(id);
//        return user;
//    }
}

