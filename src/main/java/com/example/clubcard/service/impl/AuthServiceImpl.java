package com.example.clubcard.service.impl;

import com.example.clubcard.domain.dto.request.sign.SignInRequest;
import com.example.clubcard.domain.dto.request.sign.SignUpRequest;
import com.example.clubcard.domain.dto.response.jwt.JwtAuthResponse;
import com.example.clubcard.domain.entity.User;
import com.example.clubcard.domain.mapper.UserMapper;
import com.example.clubcard.exception.CustomException;
import com.example.clubcard.exception.message.AuthErrorMessage;
import com.example.clubcard.exception.message.UserErrorMessage;
import com.example.clubcard.jwt.JwtCore;
import com.example.clubcard.service.AuthService;
import com.example.clubcard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtCore jwtCore;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public JwtAuthResponse signUp(SignUpRequest request){
        String password = request.getPassword();
        if (!Objects.equals(password, request.getConfirmPassword())){
           throw new CustomException(UserErrorMessage.PASSWORD_IS_NOT_SAME.getDescription(), HttpStatus.BAD_REQUEST);
        }

        String email = request.getEmail();
        if (userService.isExisted(email)){
            throw new CustomException(UserErrorMessage.EMAIL_EXISTS.getDescription(), HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(password));

        userService.create(user);
        String jwt = jwtCore.generateToken(user);
        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse signIn(SignInRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
        } catch(BadCredentialsException e){
            throw new CustomException(
                    AuthErrorMessage.NO_SUCH_USERNAME_OR_PWD.getMsg(),
                    AuthErrorMessage.NO_SUCH_USERNAME_OR_PWD.getStatus()
            );
        }

        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());

        String jwt = jwtCore.generateToken(userDetails);
        return new JwtAuthResponse(jwt);
    }
}
