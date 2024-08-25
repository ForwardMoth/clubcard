package com.example.clubcard.exception.message.record;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Bad request message")
public record BadRequestMessage(
        @Schema(description = "Error code", example = "400")
        int code,
        @Schema(description = "Error message", example = "BAD_REQUEST")
        String message) {

}
