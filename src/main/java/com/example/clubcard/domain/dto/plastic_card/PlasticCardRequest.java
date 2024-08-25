package com.example.clubcard.domain.dto.plastic_card;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Plastic card request")
public class PlasticCardRequest {
    @Schema(name = "cardTypeId", example = "1", defaultValue = "1")
    private Long cardTypeId;
}
