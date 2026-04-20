package com.vibe.core.exception;

/**
 * ?뱀젙 ?곗씠?곕? 議고쉶?????놁쓣 ??諛쒖깮?섎뒗 ?덉쇅
 */
public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException() {
        super(ErrorCode.ENTITY_NOT_FOUND);
    }
}

