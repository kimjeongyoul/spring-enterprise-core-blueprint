package com.vibe.modules.auth.api;

import com.vibe.modules.auth.api.dto.LoginRequest;
import com.vibe.modules.auth.api.dto.TokenResponse;
import com.vibe.modules.auth.application.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "인증 관리 API")
@Slf4j
@RestController
@RequestMapping("/api/v1/public/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인 및 토큰 발급")
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        log.info("[API] 로그인 요청: {}", request.getUserId());
        String token = authService.login(request.getUserId(), request.getPassword());
        return TokenResponse.builder().accessToken(token).build();
    }
}
