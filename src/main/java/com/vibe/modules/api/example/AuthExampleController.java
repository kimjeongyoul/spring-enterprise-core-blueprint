package com.vibe.modules.api.example;

import com.vibe.core.auth.AuthUser;
import com.vibe.core.auth.LoginUser;
import com.vibe.core.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthExampleController {

    @GetMapping("/public/hello")
    public ApiResponse<String> publicApi() {
        return ApiResponse.success("Hello, Guest!");
    }

    @GetMapping("/private/me")
    public ApiResponse<AuthUser> privateApi(@LoginUser AuthUser user) {
        return ApiResponse.success(user);
    }
}
