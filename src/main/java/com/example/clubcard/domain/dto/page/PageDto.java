package com.example.clubcard.domain.dto.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "Page request")
@AllArgsConstructor
public class PageDto {
    @Schema(name = "offset", example = "0", defaultValue = "0")
    private Integer offset;

    @Schema(name = "limit", example = "10", defaultValue = "10")
    private Integer limit;
}
