package com.vibe.core.exception;

/**
 * ?쇰컲?곸씤 鍮꾩쫰?덉뒪 ?덉쇅 泥섎━瑜??꾪븳 援ъ껜 ?대옒??
 */
public class GeneralBusinessException extends BusinessException {
    public GeneralBusinessException(ErrorCode errorCode) {
        super(errorCode);
    }

    public GeneralBusinessException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}

