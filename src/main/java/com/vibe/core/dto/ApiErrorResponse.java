package com.vibe.core.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * ?쒖? ?먮윭 ?묐떟 媛앹껜
 */
@Getter
@Builder
public class ApiErrorResponse {
    private final String code;
    private final String message;
}

