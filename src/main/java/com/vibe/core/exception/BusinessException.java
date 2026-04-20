package com.vibe.core.exception;

import lombok.Getter;

/**
 * 紐⑤뱺 鍮꾩쫰?덉뒪 ?덉쇅??理쒖긽??異붿긽 ?대옒??
 */
@Getter
public abstract class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    protected BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    protected BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}

