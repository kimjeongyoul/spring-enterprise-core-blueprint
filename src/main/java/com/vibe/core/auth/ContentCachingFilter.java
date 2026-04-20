package com.vibe.core.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

/**
 * [Security & Logging] Content Caching Filter
 * HTTP Request/Response Body를 캐싱하여 여러 번 읽을 수 있게 하며, 통합 로깅을 수행합니다.
 */
@Slf4j
@Component
public class ContentCachingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Request/Response를 캐싱 가능한 래퍼로 감쌉니다.
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long start = System.currentTimeMillis();
        
        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            long duration = System.currentTimeMillis() - start;
            logRequestResponse(requestWrapper, responseWrapper, duration);
            // 캐싱된 내용을 실제 응답 스트림으로 복사합니다 (매우 중요)
            responseWrapper.copyBodyToResponse();
        }
    }

    private void logRequestResponse(ContentCachingRequestWrapper req, ContentCachingResponseWrapper res, long duration) {
        String query = req.getQueryString();
        log.info("[HTTP] {} {} | Status: {} | Time: {}ms | Query: {}", 
                req.getMethod(), req.getRequestURI(), res.getStatus(), duration, query != null ? query : "");
        
        byte[] requestBody = req.getContentAsByteArray();
        if (requestBody.length > 0) {
            log.info("[Request Body] {}", new String(requestBody));
        }
        
        byte[] responseBody = res.getContentAsByteArray();
        if (responseBody.length > 0) {
            log.info("[Response Body] {}", new String(responseBody));
        }
    }
}
