package com.vibe.modules.application.service;

import com.vibe.core.messaging.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService extends BaseService {

    private final EventPublisher eventPublisher;

    /**
     * 二쇰Ц ?뱀씤 (?곌린 ?묒뾽)
     * rollbackFor = Exception.class瑜?紐낆떆?섏뿬 Checked Exception 諛쒖깮 ?쒖뿉???곗씠??臾닿껐??蹂댁옣.
     */
    @Transactional(rollbackFor = Exception.class)
    public void approveOrder(String orderId) {
        // 1. ?대? DB ?곹깭 ?낅뜲?댄듃
        updateOrderStatus(orderId, "APPROVED");

        // 2. ?몃? ?대깽??諛쒗뻾 (Transactional Outbox Pattern 怨좊젮)
        // ?몃옖??뀡???깃났?곸쑝濡?而ㅻ컠???꾩뿉留??몃? 硫붿떆吏媛 ?섍??꾨줉 ?ㅺ퀎?섎뒗 寃껋씠 ?뺤꽍?낅땲??
        eventPublisher.publish("order-approved-topic", orderId);
    }

    /**
     * 二쇰Ц 議고쉶 (?쎄린 ?묒뾽)
     * ?곸냽諛쏆? BaseService??readOnly = true ?ㅼ젙???섑빐 ?깅뒫 理쒖쟻?붾맖.
     */
    public Object getOrder(String orderId) {
        return "Order Data from DB";
    }

    private void updateOrderStatus(String id, String status) { /* DB 濡쒖쭅 */ }
}

