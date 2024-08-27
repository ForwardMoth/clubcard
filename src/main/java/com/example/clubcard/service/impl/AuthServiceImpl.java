package com.example.clubcard.service.impl;

import com.example.clubcard.domain.dto.jwt.JwtAuthResponse;
import com.example.clubcard.domain.dto.jwt.JwtRefreshTokenRequest;
import com.example.clubcard.domain.dto.sign.SignInRequest;
import com.example.clubcard.domain.dto.sign.SignUpRequest;
import com.example.clubcard.domain.entity.User;
import com.example.clubcard.domain.mapper.UserMapper;
import com.example.clubcard.exception.message.AuthErrorMessage;
import com.example.clubcard.exception.message.UserErrorMessage;
import com.example.clubcard.exception.type.BadRequestException;
import com.example.clubcard.exception.type.UnauthorizedException;
import com.example.clubcard.jwt.JwtCore;
import com.example.clubcard.service.AuthService;
import com.example.clubcard.service.UserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    public JwtAuthResponse signUp(SignUpRequest request) {
        String password = request.getPassword();
        if (!Objects.equals(password, request.getConfirmPassword())) {
            throw new BadRequestException(UserErrorMessage.PASSWORD_IS_NOT_SAME.getName());
        }

        String email = request.getEmail();
        if (userService.isExisted(email)) {
            throw new BadRequestException(UserErrorMessage.EMAIL_EXISTS.getName());
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(password));

        userService.create(user);
        return new JwtAuthResponse(
                jwtCore.generateToken(user, true),
                jwtCore.generateToken(user, false)
        );
    }

    public JwtAuthResponse signIn(SignInRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException(AuthErrorMessage.NO_SUCH_USERNAME_OR_PWD.getName());
        }

        User user = userService.findByEmail(request.getEmail());
        return new JwtAuthResponse(
                jwtCore.generateToken(user, true),
                jwtCore.generateToken(user, false)
        );
    }

    @Override
    public JwtAuthResponse refresh(JwtRefreshTokenRequest request) {
        try{
            String refreshToken= request.getRefreshToken();
            String email = jwtCore.extractEmail(refreshToken, false);
            User user = userService.findByEmail(email);
            return new JwtAuthResponse(
              jwtCore.generateToken(user,true),
              refreshToken
            );
        } catch (JwtException | IllegalArgumentException e){
            throw new BadRequestException(AuthErrorMessage.INVALID_OR_EXPIRED_TOKEN.getName());
        }
    }
}
