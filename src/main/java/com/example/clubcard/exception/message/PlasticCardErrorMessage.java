package com.example.clubcard.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlasticCardErrorMessage {
    PLASTIC_CARD_FOR_USER_NOT_FOUND("Plastic card for such user isn't found "),
    PLASTIC_CARD_NOT_FOUND("Plastic card with such id isn't found"),
    PLASTIC_CARD_TYPE_EXISTS("Plastic card with such user exists"),
    CANT_UPDATE_READY_PLASTIC_CARD("Can't update status for ready card");

    private final String name;

}
