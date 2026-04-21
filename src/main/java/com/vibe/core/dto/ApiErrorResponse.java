package com.vibe.core.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 표준 에러 응답 객체
 */
@Getter
@Builder
public class ApiErrorResponse {
    private final String code;
    private final String message;
}
