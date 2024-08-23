package com.example.clubcard.domain.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "User profile update request")
public class UserUpdateRequest {
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

    @Schema(description = "Birthday", example = "15.08.1999")
    @Size(max = 10, message = "Length of birthday must be no more than 11 symbols")
    @NotBlank(message = "Birthday can't be empty")
    private String birthday;

    @Schema(description = "Phone number", example = "81995414801")
    @Size(min=3,max=20, message = "Phone number must contain from 3 to 20 symbols")
    @NotBlank(message = "Phone number can't be empty")
    private String phoneNumber;
}
