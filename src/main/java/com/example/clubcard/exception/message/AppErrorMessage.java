package com.example.clubcard.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppErrorMessage {
    INCORRECT_ATTRIBUTE_NAME("Incorrect attribute name for sorting");

    private final String name;
}
