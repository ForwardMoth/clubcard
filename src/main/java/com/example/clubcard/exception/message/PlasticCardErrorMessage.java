package com.example.clubcard.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PlasticCardErrorMessage {
    PLASTIC_CARD_TYPE_NOT_FOUND("Plastic card isn't found for such user", HttpStatus.NOT_FOUND),
    PLASTIC_CARD_TYPE_EXISTS("Plastic card to such user is exists", HttpStatus.BAD_REQUEST),
    CANT_UPDATE_READY_PLASTIC_CARD("Can't update status for ready card", HttpStatus.BAD_REQUEST);

    private final String msg;
    private final HttpStatus status;

}
