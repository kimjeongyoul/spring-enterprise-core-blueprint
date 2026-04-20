package com.vibe.core.exception;

import com.vibe.core.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.error("[Business Error] {}: {}", errorCode.getCode(), e.getMessage());

        // 토스 스타일: { "code": "...", "message": "..." }
        return new ResponseEntity<>(
                ApiErrorResponse.builder()
                        .code(errorCode.getCode())
                        .message(e.getMessage())
                        .build(),
                errorCode.getStatus()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGlobalException(Exception e) {
        log.error("[System Error] ", e);
        return new ResponseEntity<>(
                ApiErrorResponse.builder()
                        .code("INTERNAL_SERVER_ERROR")
                        .message("서버 오류가 발생했습니다.")
                        .build(),
                org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
