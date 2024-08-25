package com.example.clubcard.exception.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BadRequestException extends RuntimeException{
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
