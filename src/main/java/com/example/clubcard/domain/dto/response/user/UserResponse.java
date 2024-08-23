package com.example.clubcard.domain.dto.response.user;


import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Full user response information")
public class UserResponse {
    @Schema(description = "First name", example = "Ivan")
    private String firstName;

    @Schema(description = "Last name", example = "Ivanov")
    private String lastName;

    @Schema(description = "Second name", example = "Ivanovich")
    private String secondName;

    @Schema(description = "Birthday", example = "15.08.1999")
    private String birthday;

    @Schema(description = "Phone number", example = "81995414801")
    private String phoneNumber;

    @Schema(description = "Email", example = "user@mail.com")
    private String email;

    @Schema(description = "Money", example = "100")
    private Integer money;

    @Schema(name = "Block status for user", example = "false")
    private Boolean isBlocked;

    @Schema(name = "User privilege")
    private PrivilegeResponse privilegeResponse;
}
