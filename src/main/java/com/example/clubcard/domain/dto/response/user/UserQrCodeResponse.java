package com.example.clubcard.domain.dto.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User qr-code response")
public class UserQrCodeResponse {
    @Schema(name = "uuid", example = "262f076b-d35a-4923-b619-486b5cbef867")
    private String uuid;
}
