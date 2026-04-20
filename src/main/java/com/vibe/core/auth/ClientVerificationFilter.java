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
        if (path.contains("swagger") || path.contains("api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        String clientKey = request.getHeader("X-VIBE-CLIENT-KEY");

        if (clientKey == null || !clientKey.equals(validClientKey)) {
            log.warn("[Security Alert] 미인증 클라이언트 접근 차단: {}", path);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Client");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
