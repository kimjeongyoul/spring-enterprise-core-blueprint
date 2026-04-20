package com.vibe.modules.api.example;

import com.vibe.core.auth.AuthUser;
import com.vibe.core.auth.JwtProvider;
import com.vibe.core.auth.LoginUser;
import com.vibe.core.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "인증 테스트 API")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthExampleController {

    private final JwtProvider jwtProvider;

    @Operation(summary = "테스트용 JWT 토큰 발급", description = "로그인 과정 없이 즉시 테스트용 토큰을 생성합니다.")
    @GetMapping("/public/auth/test-token")
    public ApiResponse<String> getTestToken() {
        String token = jwtProvider.createToken("admin-vibe", "ROLE_ADMIN");
        return ApiResponse.success(token);
    }

    @Operation(summary = "비로그인 API 테스트")
    @GetMapping("/public/hello")
    public ApiResponse<String> publicApi() {
        return ApiResponse.success("Hello, Guest!");
    }

    @Operation(summary = "로그인 정보 주입 테스트")
    @GetMapping("/private/me")
    public ApiResponse<AuthUser> privateApi(@LoginUser AuthUser user) {
        return ApiResponse.success(user);
    }
}
