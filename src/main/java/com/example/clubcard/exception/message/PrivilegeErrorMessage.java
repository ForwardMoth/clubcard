package com.example.clubcard.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PrivilegeErrorMessage {
    PRIVILEGE_NOT_FOUND("Privilege isn't found", HttpStatus.NOT_FOUND),
    PRIVILEGE_EXISTS("Privilege with such name is exists", HttpStatus.BAD_REQUEST);

    private final String msg;
    private final HttpStatus status;
}