package com.vibe.core.config;

import com.vibe.core.dto.ApiErrorResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * [Standard] Global Response Handler
 * 모든 성공 응답을 가로채어 형식을 확인하거나 공통 처리를 수행합니다.
 */
@RestControllerAdvice(basePackages = "com.vibe.modules")
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        
        // 에러 응답은 이미 형식이 갖춰져 있으므로 통과
        if (body instanceof ApiErrorResponse) return body;
        
        // 토스 스타일(Direct Return)을 유지하되, 여기서 로그를 남기거나 
        // 특정 필드를 공통으로 추가하는 등의 "형식 강제" 로직을 수행할 수 있습니다.
        return body;
    }
}
