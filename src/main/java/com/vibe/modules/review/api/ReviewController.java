package com.vibe.modules.review.api;

import com.vibe.core.auth.AuthUser;
import com.vibe.core.auth.LoginUser;
import com.vibe.modules.review.api.dto.ReviewCreateRequest;
import com.vibe.modules.review.application.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Review", description = "리뷰 관리 API")
@RestController
@RequestMapping("/api/v1/private/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 등록")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createReview(
            @LoginUser AuthUser user,
            @RequestBody ReviewCreateRequest request
    ) {
        return reviewService.registerReview(user.getUserId(), request);
    }
}
