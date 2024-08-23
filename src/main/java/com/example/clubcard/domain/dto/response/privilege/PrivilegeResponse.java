package com.example.clubcard.domain.dto.response.privilege;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Privilege response")
public class PrivilegeResponse {
    @Schema(name = "Privilege id", example = "1")
    private Long id;

    @Schema(name = "Privilege name", example = "standard")
    private String name;

    @Schema(name = "Privilege description", example = "standard privilege for all users")
    private String description;
}
