package com.vibe.modules.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * [Standard] ?쒕퉬???덉씠???몃옖??뀡 ?꾨왂
 * 
 * 1. ?대옒???덈꺼 @Transactional(readOnly = true): 
 *    - Dirty Checking??嫄대꼫?곗뼱 硫붾え由?諛?CPU ?깅뒫 理쒖쟻??
 *    - DB 蹂듭젣(Replication) ?섍꼍?먯꽌 Read ?꾩슜 ?몃뱶濡??좊룄.
 * 
 * 2. 硫붿꽌???덈꺼 @Transactional(rollbackFor = Exception.class):
 *    - 湲곕낯?곸쑝濡??고????덉쇅留?濡ㅻ갚?섎뒗 臾몄젣瑜?諛⑹??섍린 ?꾪빐 紐⑤뱺 ?덉쇅?????濡ㅻ갚 ?섑뻾.
 */
@Slf4j
@Transactional(readOnly = true)
public abstract class BaseService {
    // 怨듯넻 ?쒕퉬??濡쒖쭅...
}

