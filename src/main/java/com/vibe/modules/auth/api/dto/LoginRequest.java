package com.vibe.modules.auth.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "로그인 요청")
public class LoginRequest {
    @Schema(description = "사용자 아이디", example = "admin-vibe")
    private String userId;
    
    @Schema(description = "비밀번호", example = "password123")
    private String password;
}
