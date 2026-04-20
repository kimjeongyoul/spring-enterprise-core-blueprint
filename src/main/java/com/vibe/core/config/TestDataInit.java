package com.vibe.core.config;

import com.vibe.modules.user.domain.User;
import com.vibe.modules.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * [Standard] 초기 테스트 데이터 로더
 * 로컬/개발 환경에서 서버 시작 시 기본 테스트 계정을 생성합니다.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@Profile({"local", "default"}) // 프로덕션에서는 실행되지 않도록 설정
public class TestDataInit implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUserId("admin-vibe").isEmpty()) {
            log.info("[System] 초기 테스트 데이터 생성 중: admin-vibe / password123");
            
            User testUser = User.builder()
                    .userId("admin-vibe")
                    .password(passwordEncoder.encode("password123"))
                    .role("ROLE_ADMIN")
                    .build();
            
            userRepository.save(testUser);
            log.info("[System] 초기 테스트 데이터 생성 완료!");
        }
    }
}
