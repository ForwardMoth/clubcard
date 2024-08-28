package com.example.clubcard.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorMessage {
    EMAIL_EXISTS("User with such email exists"),
    PASSWORD_IS_NOT_SAME("Passwords are not the same"),
    ROLE_NOT_FOUND("Role isn't found"),
    USER_IS_NOT_FOUND("User with such id is not found"),
    NOT_ENOUGH_MONEY("User don't have enough point to update privilege"),
    CANT_UPDATE_FOR_BLOCKED("Can't update date for blocked user"),
    BALANCE_CANT_BE_NEGATIVE("Balance can't be negative");

    private final String name;
}
