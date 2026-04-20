package com.vibe.modules.order.application.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * [Application Layer DTO / Command]
 * - 서비스 내부에서 사용되는 데이터 뭉치
 * - API 레이어의 Request DTO가 서비스 레이어까지 침투하지 않도록 방어하는 역할을 합니다.
 */
@Getter
@Builder
public class OrderCommand {
    private String userId;
    private String productName;
    private Integer quantity;
}
