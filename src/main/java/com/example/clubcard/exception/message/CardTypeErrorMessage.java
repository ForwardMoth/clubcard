package com.example.clubcard.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardTypeErrorMessage {
    CARD_TYPE_NOT_FOUND("Card type isn't found"),
    CARD_TYPE_EXISTS("Card type with such name is exists"),
    CANT_DELETE_CARD_TYPE("Some users had this card type!");

    private final String name;
}
