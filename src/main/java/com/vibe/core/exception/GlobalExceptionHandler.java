package com.vibe.core.exception;

import com.vibe.core.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ?곕━媛 ?뺤쓽??紐⑤뱺 鍮꾩쫰?덉뒪 ?덉쇅 泥섎━
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.error("[Business Error] {}: {}", errorCode.getCode(), e.getMessage());

        ApiErrorResponse response = ApiErrorResponse.builder()
                .code(errorCode.getCode())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    /**
     * 洹????뺤쓽?섏? ?딆? ?쒖뒪???먮윭 泥섎━
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiErrorResponse> handleGlobalException(Exception e) {
        log.error("[System Error] ", e);
        
        ApiErrorResponse response = ApiErrorResponse.builder()
                .code(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
                .message(ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();

        return new ResponseEntity<>(response, ErrorCode.INTERNAL_SERVER_ERROR.getStatus());
    }
}

