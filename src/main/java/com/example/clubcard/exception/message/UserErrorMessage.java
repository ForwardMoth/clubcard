package com.example.clubcard.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorMessage {
    EMAIL_EXISTS("User with such email exists"),
    PASSWORD_IS_NOT_SAME("Passwords are not the same"),
    PRIVILEGE_NOT_FOUND("Privilege isn't found"),
    ROLE_NOT_FOUND("Role isn't found"),
    USER_IS_NOT_FOUND("User with such id is not found");

    private final String description;
}
