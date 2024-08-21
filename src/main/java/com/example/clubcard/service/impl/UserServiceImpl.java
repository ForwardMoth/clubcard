package com.example.clubcard.service.impl;

import com.example.clubcard.domain.enums.RoleEnum;
import com.example.clubcard.domain.model.User;
import com.example.clubcard.repository.RoleRepository;
import com.example.clubcard.repository.UserRepository;
import com.example.clubcard.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User create(User user){
        user.setRole(roleRepository.findByName(RoleEnum.USER.name()));
        return userRepository.save(user);
    }

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Boolean isExisted(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User with '%s' isn't found", username)));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getAuthorities()
        );
    }
}
