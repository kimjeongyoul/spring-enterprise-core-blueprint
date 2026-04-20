package com.vibe.modules.auth.application;

import com.vibe.core.auth.JwtProvider;
import com.vibe.modules.user.infrastructure.UserRepository;
import com.vibe.modules.user.domain.User;
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

    @Transactional(readOnly = true)
    public String login(String userId, String password) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new GeneralBusinessException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new GeneralBusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }

        return jwtProvider.createToken(user.getUserId(), user.getRole());
    }
}
