package com.example.clubcard.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User balance response")
public class UserBalanceResponse {
    @Schema(description = "User id", example = "1")
    private Long userId;

    @Schema(description = "Money", example = "100")
    private Integer money;
}
