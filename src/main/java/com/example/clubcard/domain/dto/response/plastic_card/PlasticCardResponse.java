package com.example.clubcard.domain.dto.response.plastic_card;

import com.example.clubcard.domain.dto.response.card_type.CardTypeResponse;
import com.example.clubcard.domain.dto.response.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Plastic card response")
public class PlasticCardResponse {
    @Schema(name = "id")
    private Long id;

    @Schema(name = "status")
    private String status;

    @Schema(name = "createdAt")
    private Date createdAt;

    @Schema(name = "user")
    private UserResponse userResponse;

    @Schema(name = "cardTypeId")
    private CardTypeResponse cardTypeResponse;
}
