package com.example.clubcard.domain.dto.card_type;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Card type response")
public class CardTypeResponse {
    @Schema(name = "id", example = "1")
    private Long id;

    @Schema(name = "name", example = "standard")
    private String name;

    @Schema(name = "description", example = "standard privilege for all users")
    private String description;
}
