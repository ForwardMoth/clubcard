package com.example.clubcard.domain.dto.response.privilege;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Privilege response")
public class PrivilegeResponse {
    @Schema(name = "Id", example = "1")
    private Long id;

    @Schema(name = "Name", example = "standard")
    private String name;

    @Schema(name = "Description", example = "standard privilege for all users")
    private String description;

    @Schema(name = "Price", example = "200")
    private Integer price;
}
