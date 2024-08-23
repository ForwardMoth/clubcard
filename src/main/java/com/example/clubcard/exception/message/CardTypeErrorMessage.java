package com.example.clubcard.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CardTypeErrorMessage {

    CARD_TYPE_NOT_FOUND("Card type isn't found", HttpStatus.NOT_FOUND),
    CARD_TYPE_EXISTS("Card type with such name is exists", HttpStatus.BAD_REQUEST),
    CANT_DELETE_CARD_TYPE("Some users had this card type!", HttpStatus.BAD_REQUEST);

    private final String msg;
    private final HttpStatus status;
}
