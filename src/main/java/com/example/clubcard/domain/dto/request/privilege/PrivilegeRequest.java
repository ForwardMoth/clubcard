package com.example.clubcard.domain.dto.request.privilege;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Privilege request")
public class PrivilegeRequest {

    @Schema(name = "privilegeId", example = "1")
    @NotNull(message = "Privilege id can't be empty")
    private Long privilegeId;
}
