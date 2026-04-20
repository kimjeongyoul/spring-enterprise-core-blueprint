package com.vibe.core.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class ClientVerificationFilter extends OncePerRequestFilter {

    @Value("${app.client-key}")
    private String validClientKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String path = request.getRequestURI();
        log.info("[Security Handshake] Request Path: {}", path);

        // 1. 제외 경로 (Swagger 및 Public API는 클라이언트 키 체크 패스)
        if (path.contains("swagger") || path.contains("api-docs") || path.contains("/public/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Private API는 X-VIBE-CLIENT-KEY 필수
        String clientKey = request.getHeader("X-VIBE-CLIENT-KEY");

        if (clientKey == null || !clientKey.equals(validClientKey)) {
            log.warn("[Security Alert] 미인증 클라이언트 접근 거부: {}", path);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized Client Key");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
