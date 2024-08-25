package com.example.clubcard.domain.dto.response.user;


import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Full user response information")
public class UserResponse {
    @Schema(description = "UserId", example = "1")
    private Long userId;

    @Schema(description = "firstName", example = "Ivan")
    private String firstName;

    @Schema(description = "lastName", example = "Ivanov")
    private String lastName;

    @Schema(description = "SecondName", example = "Ivanovich")
    private String secondName;

    @Schema(description = "Birthday", example = "15.08.1999")
    private String birthday;

    @Schema(description = "PhoneNumber", example = "81995414801")
    private String phoneNumber;

    @Schema(description = "Email", example = "user@mail.com")
    private String email;

    @Schema(description = "Money", example = "100")
    private Integer money;

    @Schema(name = "isBlocked", example = "false")
    private Boolean isBlocked;

    @Schema(name = "uuid", example = "9c508efd-d7a7-423f-a4d1-cb5389909fbf")
    private String uuid;

    @Schema(name = "Privilege")
    private PrivilegeResponse privilegeResponse;
}
