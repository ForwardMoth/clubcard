package com.example.clubcard.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Registration request")
public class SignUpRequest {
    @Schema(description = "Email", example = "user@mail.com")
    @Size(min=5, max=255, message = "Email must contain from 5 to 255 symbols")
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email must be in the format user@example.com")
    private String email;

    @Schema(description = "Password", example = "password")
    @Size(min=4, max=25, message = "Length of password must be no more than 25 symbols")
    @NotBlank(message = "Password can't be empty")
    private String password;

    @Schema(description = "Confirm password", example = "password")
    @Size(min=4, max=25, message = "Length of confirm password must be no more than 25 symbols")
    @NotBlank(message = "Confirm password can't be empty")
    private String confirmPassword;
}
