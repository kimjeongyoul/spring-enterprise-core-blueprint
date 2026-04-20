package com.vibe.modules.review.application;

import com.vibe.modules.common.application.BaseService;
import com.vibe.modules.review.api.dto.ReviewCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService extends BaseService {

    /**
     * 리뷰 등록
     * 주문 상태 확인 등 타 도메인과의 연계 로직이 포함될 수 있습니다.
     */
    @Transactional(rollbackFor = Exception.class)
    public Long registerReview(String userId, ReviewCreateRequest request) {
        log.info("[Service] 리뷰 등록: 사용자={}, 주문={}", userId, request.getOrderId());
        
        // 1. 주문 완료 여부 확인 로직 (실제로는 OrderService 호출)
        // 2. 리뷰 엔티티 생성 및 저장
        
        return 1L; // 생성된 리뷰 ID
    }
}
