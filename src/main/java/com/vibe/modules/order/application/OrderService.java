package com.vibe.modules.order.application;

import com.vibe.modules.common.application.BaseService;
import com.vibe.core.messaging.EventPublisher;
import com.vibe.modules.order.api.dto.OrderResponse;
import com.vibe.modules.order.application.dto.OrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService extends BaseService {

    private final EventPublisher eventPublisher;

    /**
     * 주문 승인 (쓰기 작업)
     * rollbackFor = Exception.class를 명시하여 Checked Exception 발생 시에도 데이터 무결성 보장.
     */
    @Transactional(rollbackFor = Exception.class)
    public void approveOrder(OrderCommand command) {
        updateOrderStatus(command.getProductName(), "APPROVED");
        eventPublisher.publish("order-approved-topic", command.getProductName());
    }

    /**
     * 주문 목록 조회 (읽기 작업, 페이징)
     * QueryDSL Repository를 사용하여 복잡한 검색 조건과 페이징 처리를 수행하는 것이 시니어의 모범 사례입니다.
     */
    public Page<OrderResponse> getOrders(String userId, Pageable pageable) {
        // 실제로는 repository.findAllByUserId(userId, pageable) 호출
        List<OrderResponse> dummyList = List.of(
            OrderResponse.builder().orderId("ORD_001").status("COMPLETED").build(),
            OrderResponse.builder().orderId("ORD_002").status("PENDING").build()
        );

        return new PageImpl<>(dummyList, pageable, 100);
    }

    private void updateOrderStatus(String id, String status) { /* DB 로직 */ }
}
