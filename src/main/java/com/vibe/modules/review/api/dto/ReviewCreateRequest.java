package com.vibe.modules.review.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "리뷰 등록 요청")
public class ReviewCreateRequest {
    @Schema(description = "주문 ID", example = "ORD_12345")
    private String orderId;
    
    @Schema(description = "리뷰 내용", example = "배송이 빠르고 상품이 좋습니다!")
    private String content;
    
    @Schema(description = "별점 (1-5)", example = "5")
    private Integer rating;
}
