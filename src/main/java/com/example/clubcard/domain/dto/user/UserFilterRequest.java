package com.example.clubcard.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "User filter request")
@AllArgsConstructor
public class UserFilterRequest {
    @Schema(name = "lastName", example = "ivanov")
    private String lastName;

    @Schema(name = "privilegeId", example = "1")
    private Long privilegeId;

    @Schema(name = "isBlocked", example = "false")
    private Boolean isBlocked;
}
