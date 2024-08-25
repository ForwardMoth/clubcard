package com.example.clubcard.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AppErrorMessage {
    INCORRECT_ATTRIBUTE_NAME("Incorrect attribute name for sorting", HttpStatus.BAD_REQUEST);

    private final String msg;
    private final HttpStatus status;
}
