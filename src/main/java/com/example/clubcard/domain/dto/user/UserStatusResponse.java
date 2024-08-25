package com.example.clubcard.domain.dto.user;

import com.example.clubcard.domain.dto.privilege.PrivilegeResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "User status response")
@AllArgsConstructor
public class UserStatusResponse {
    @Schema(description = "User id", example = "1")
    private Long userId;

    @Schema(name = "isBlocked", example = "false")
    private Boolean isBlocked;

    @Schema(name = "Privilege")
    private PrivilegeResponse privilegeResponse;
}
