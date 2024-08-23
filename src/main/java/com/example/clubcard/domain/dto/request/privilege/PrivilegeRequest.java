package com.example.clubcard.domain.dto.request.privilege;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Privilege request")
public class PrivilegeRequest {

    @Schema(name = "privilege_id", example = "1")
    @NotBlank(message = "Privilege id can't be empty")
    private Long privilegeId;
}
