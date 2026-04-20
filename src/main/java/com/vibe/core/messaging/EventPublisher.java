package com.vibe.core.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventPublisher {
    public void publish(String topic, Object payload) {
        log.info("[Event] Topic: {}, Payload: {}", topic, payload);
    }
}

