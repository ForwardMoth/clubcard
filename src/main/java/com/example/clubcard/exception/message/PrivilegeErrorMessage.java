package com.example.clubcard.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PrivilegeErrorMessage {
    PRIVILEGE_NOT_FOUND("Privilege isn't found"),
    PRIVILEGE_EXISTS("Privilege with such name is exists"),
    CANT_DELETE_PRIVILEGE("Some users had this privilege!");

    private final String name;
}