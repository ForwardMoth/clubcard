package com.example.clubcard.exception.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
