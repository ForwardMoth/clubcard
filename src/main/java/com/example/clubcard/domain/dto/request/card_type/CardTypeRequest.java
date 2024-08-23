package com.example.clubcard.domain.dto.request.card_type;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "card type request")
public class CardTypeRequest {
    @Schema(name = "name", example = "standard")
    @Size(max=50, message = "Length of name must be no more than 50 symbols")
    @NotBlank(message = "Name can't be empty")
    private String name;

    @Schema(name = "description", example = "standard privilege for all users")
    @Size(max=50, message = "Length of description must be no more than 50 symbols")
    @NotBlank(message = "Description can't be empty")
    private String description;
}
