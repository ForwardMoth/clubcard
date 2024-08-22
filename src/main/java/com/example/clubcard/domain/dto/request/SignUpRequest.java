package com.example.clubcard.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "Registration request")
public class SignUpRequest {
    @Schema(description = "Email", example = "user@mail.com")
    @Size(min=5, max=255, message = "Email must contain from 5 to 255 symbols")
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email must be in the format user@example.com")
    private String email;

    @Schema(description = "Password", example = "password")
    @Size(min=4, max=25, message = "Password must contain from 4 to 25 symbols")
    @NotBlank(message = "Password can't be empty")
    private String password;

    @Schema(description = "Confirm password", example = "password")
    @Size(min=4, max=25, message = "Confirm password must contain from 4 to 25 symbols")
    @NotBlank(message = "Confirm password can't be empty")
    private String confirmPassword;

    @Schema(description = "First name", example = "Ivan")
    @Size(max=50, message = "Length of first name must be no more than 50 symbols")
    @NotBlank(message = "First name can't be empty")
    private String firstName;

    @Schema(description = "Last name", example = "Ivanov")
    @Size(max=50, message = "Length of last name must be no more than 50 symbols")
    @NotBlank(message = "Last name can't be empty")
    private String lastName;

    @Schema(description = "Second name", example = "Ivanovich")
    @Size(max=50, message = "Length of second name must be no more than 50 symbols")
    private String secondName;

    @Schema(description = "Phone number", example = "81995414801")
    @Size(min=3,max=20, message = "Phone number must contain from 3 to 20 symbols")
    @NotBlank(message = "Phone number can't be empty")
    private String phoneNumber;

    @Schema(description = "Birthday", example = "15.08.1999")
    @Size(max = 10, message = "Length of birthday must be no more than 11 symbols")
    private String birthday;
}
