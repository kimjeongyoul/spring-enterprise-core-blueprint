package com.vibe.modules.application.service;

import com.vibe.core.auth.JwtProvider;
import com.vibe.core.persistence.UserRepository;
import com.vibe.modules.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vibe.core.exception.GeneralBusinessException;
import com.vibe.core.exception.ErrorCode;

/**
 * [OWASP Compliant] 인증 서비스
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(String userId, String password) {
        if (userRepository.findByUserId(userId).isPresent()) {
            throw new GeneralBusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .userId(userId)
                .password(encodedPassword)
                .role("ROLE_USER")
                .build();
        
        userRepository.save(user);
    }
}
