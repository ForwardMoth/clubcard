package com.example.clubcard.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorMessage {
    NO_SUCH_USERNAME_OR_PWD("Incorrect email or password", HttpStatus.UNAUTHORIZED);

    private final String msg;
    private final HttpStatus status;
}
