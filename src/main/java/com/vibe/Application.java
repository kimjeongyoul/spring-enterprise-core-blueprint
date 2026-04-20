package com.vibe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        log.info("====================================================================");
        log.info("🚀 Enterprise Core Blueprint is Ready!");
        log.info("📌 Swagger UI: http://localhost:8080/swagger-ui/index.html");
        log.info("🔑 Required Header: X-VIBE-CLIENT-KEY = vibe-client-secret-key-2026");
        log.info("👤 Test Login API: /api/v1/public/auth/test-token");
        log.info("====================================================================");
    }
}
