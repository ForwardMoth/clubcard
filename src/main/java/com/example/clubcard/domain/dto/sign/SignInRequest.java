package com.example.clubcard.domain.dto.sign;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Login request")
public class SignInRequest {
    @Schema(description = "Email", example = "user@mail.com")
    @Size(min=5, max=255, message = "Email must contain from 5 to 255 symbols")
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email must be in the format user@example.com", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Schema(description = "Password", example = "password")
    @Size(min=4, max=25, message = "Length of password must be no more than 25 symbols")
    @NotBlank(message = "Password can't be empty")
    private String password;
}
