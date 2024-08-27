package com.example.clubcard.domain.dto.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Jwt refresh token request")
public class JwtRefreshTokenRequest {
    @Schema(name = "refreshToken", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NT...")
    @NotBlank(message = "Refresh token can't be empty")
    private String refreshToken;
}
