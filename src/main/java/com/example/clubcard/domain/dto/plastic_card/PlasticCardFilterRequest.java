package com.example.clubcard.domain.dto.plastic_card;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@Schema(description = "plastic card filter request")
@AllArgsConstructor
public class PlasticCardFilterRequest {
    @Schema(name = "status", example = "PROGRESS")
    private String status;

    @Schema(name = "createdAt", example = "2024-08-22T15:22:53.403Z")
    private Instant createdAt;

    @Schema(name = "cardTypeId")
    private Long cardTypeId;
}
