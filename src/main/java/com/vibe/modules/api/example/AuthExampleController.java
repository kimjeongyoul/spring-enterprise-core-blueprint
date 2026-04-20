package com.vibe.modules.api.example;

import com.vibe.core.auth.AuthUser;
import com.vibe.core.auth.JwtProvider;
import com.vibe.core.auth.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Auth", description = "인증 테스트 API")
@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthExampleController {

    private final JwtProvider jwtProvider;

    @Operation(summary = "테스트용 JWT 토큰 발급")
    @GetMapping("/public/auth/test-token")
    public Map<String, String> getTestToken() {
        log.info("[API] 테스트 토큰 발급 요청");
        String token = jwtProvider.createToken("admin-vibe", "ROLE_ADMIN");
        // 토스 스타일: 불필요한 SUCCESS 래퍼 없이 데이터만 반환
        return Map.of("accessToken", token);
    }

    @Operation(summary = "로그인 정보 주입 테스트")
    @GetMapping("/private/me")
    public AuthUser privateApi(@LoginUser AuthUser user) {
        log.info("[API] 내 정보 조회 요청: {}", user.getUserId());
        return user;
    }
}
