package com.vibe.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * [Standard] 전역 에러 코드 정의
 * 에러의 성격에 따라 HTTP 상태 코드와 비즈니스 코드를 매핑합니다.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 공통 에러
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON_001", "잘못된 입력값입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON_002", "허용되지 않은 메서드입니다."),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON_003", "존재하지 않는 리소스입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_004", "서버 내부 오류가 발생했습니다."),

    // 인증/권한 관련
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH_001", "인증되지 않은 사용자입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "AUTH_002", "접근 권한이 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH_003", "사용자를 찾을 수 없습니다."),

    // 도메인 특화 에러 (예: 결제)
    ALREADY_APPROVED(HttpStatus.CONFLICT, "PAY_001", "이미 승인된 결제건입니다."),
    EXCEEDED_AMOUNT(HttpStatus.BAD_REQUEST, "PAY_002", "한도를 초과하는 금액입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
