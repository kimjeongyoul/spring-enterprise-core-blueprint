package com.vibe.core.exception;

/**
 * [Exception] 특정 데이터를 조회할 수 없을 때 발생하는 예외
 */
public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
}
