package com.example.clubcard.exception.message.record;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Not Found")
public record NotFoundMessage(
        @Schema(description = "Error code", example = "404")
        int code,
        @Schema(description = "Error message", example = "NOT FOUND")
        String message) {
}
