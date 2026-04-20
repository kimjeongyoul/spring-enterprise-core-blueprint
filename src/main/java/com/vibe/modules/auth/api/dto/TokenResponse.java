package com.vibe.modules.auth.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "로그인 응답 (토큰)")
public class TokenResponse {
    @Schema(description = "Access Token")
    private String accessToken;
    
    @Schema(description = "토큰 타입", example = "Bearer")
    private final String tokenType = "Bearer";
}
