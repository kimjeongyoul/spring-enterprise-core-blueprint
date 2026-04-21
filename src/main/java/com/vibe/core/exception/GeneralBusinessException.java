package com.vibe.core.exception;

/**
 * [Exception] 일반적인 비즈니스 예외 처리를 위한 구체 클래스
 */
public class GeneralBusinessException extends BusinessException {
    public GeneralBusinessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
