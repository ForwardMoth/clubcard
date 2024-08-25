package com.example.clubcard.exception.message.record;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
@Schema(name = "Unauthorized message")
public record UnauthorizedMessage(
        @Schema(description = "Error code", example = "401")
        int code,
        @Schema(description = "Error message", example = "UNAUTHORIZED")
        String message) {
}