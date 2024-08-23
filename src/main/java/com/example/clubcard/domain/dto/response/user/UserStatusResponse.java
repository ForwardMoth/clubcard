package com.example.clubcard.domain.dto.response.user;

import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "User status response")
@AllArgsConstructor
public class UserStatusResponse {
    @Schema(name = "Block status for user", example = "false")
    private Boolean isBlocked;

    @Schema(name = "User privilege")
    private PrivilegeResponse privilegeResponse;
}
