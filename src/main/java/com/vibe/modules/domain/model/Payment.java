package com.vibe.modules.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Payment {
    private String paymentKey;
    private String orderId;
    private Long amount;
    private String status;
    private LocalDateTime approvedAt;
}

