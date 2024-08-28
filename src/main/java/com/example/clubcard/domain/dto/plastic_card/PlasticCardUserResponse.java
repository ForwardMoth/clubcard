package com.example.clubcard.domain.dto.plastic_card;

import com.example.clubcard.domain.dto.card_type.CardTypeResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;


@Data
@Schema(description = "Plastic card user response")
public class PlasticCardUserResponse {
    @Schema(name = "id")
    private Long id;

    @Schema(name = "status")
    private String status;

    @Schema(name = "createdAt")
    private Instant createdAt;

    @Schema(name = "cardTypeId")
    private CardTypeResponse cardTypeResponse;
}
