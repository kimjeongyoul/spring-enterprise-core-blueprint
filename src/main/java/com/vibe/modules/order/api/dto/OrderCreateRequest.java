package com.vibe.modules.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [API Layer Request DTO]
 * - 외부에서 들어오는 데이터 규격
 * - Bean Validation 등을 여기서 수행
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "주문 생성 요청")
public class OrderCreateRequest {
    @Schema(description = "상품명", example = "MacBook Pro")
    private String productName;
    
    @Schema(description = "주문 수량", example = "1")
    private Integer quantity;
}
