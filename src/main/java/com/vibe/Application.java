package com.vibe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Enterprise Core Blueprint Application
 * JPA Auditing 疫꿸퀡?????뽮쉐?酉釉???怨쀬뵠???????곕뗄?????뽰삂??몃빍??
 */
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

