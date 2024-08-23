package com.example.clubcard.domain.dto.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "User balance response")
public class UserBalanceResponse {
    @Schema(description = "Money", example = "100")
    private Integer money;
}
