package com.vibe.modules.order.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

/**
 * [API Layer Response DTO]
 * - 클라이언트에게 노출할 데이터 규격
 * - 엔티티(Entity)를 직접 반환하지 않고 이 DTO로 변환하여 반환하는 것이 엔터프라이즈 표준입니다.
 */
@Getter
@Builder
@Schema(description = "주문 응답 정보")
public class OrderResponse {
    @Schema(description = "주문 ID")
    private String orderId;
    
    @Schema(description = "주문 상태")
    private String status;
    
    @Schema(description = "영수증 URL")
    private String receiptUrl;
}
