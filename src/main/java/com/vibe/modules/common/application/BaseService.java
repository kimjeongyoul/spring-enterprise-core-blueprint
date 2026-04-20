package com.vibe.modules.common.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * [Standard] 서비스 레이어 트랜잭션 전략
 * 
 * 1. 클래스 레벨 @Transactional(readOnly = true): 
 *    - Dirty Checking을 건너뛰어 메모리 및 CPU 성능 최적화.
 *    - DB 복제(Replication) 환경에서 Read 전용 노드로 유도.
 * 
 * 2. 메서드 레벨 @Transactional(rollbackFor = Exception.class):
 *    - 기본적으로 런타임 예외만 롤백되는 문제를 방지하기 위해 모든 예외에 대해 롤백 수행.
 */
@Slf4j
@Transactional(readOnly = true)
public abstract class BaseService {
    // 공통 서비스 로직...
}
