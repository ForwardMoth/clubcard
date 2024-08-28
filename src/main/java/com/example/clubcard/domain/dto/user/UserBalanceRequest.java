package com.example.clubcard.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "User balance request")
public class UserBalanceRequest {

    @Schema(description = "Money", example = "100")
    private Integer money;
}
