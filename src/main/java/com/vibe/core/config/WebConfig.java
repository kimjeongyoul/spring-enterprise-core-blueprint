package com.vibe.core.config;

import com.vibe.core.auth.LoginUser;
import com.vibe.core.auth.AuthUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * [Senior Support] Multipart 요청 시 Content-Type이 누락된 파트(octet-stream)를 JSON으로 처리
     * 
     * 원인: 스웨거 등에서 multipart/form-data를 보낼 때 JSON 파트의 타입을 명시하지 않아 500 에러 발생.
     * 해결: MappingJackson2HttpMessageConverter가 application/octet-stream도 지원하도록 지능적으로 확장.
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter jsonConverter) {
                List<MediaType> mediaTypes = new java.util.ArrayList<>(jsonConverter.getSupportedMediaTypes());
                mediaTypes.add(new MediaType("application", "octet-stream"));
                jsonConverter.setSupportedMediaTypes(mediaTypes);
            }
        }
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return parameter.hasParameterAnnotation(LoginUser.class) && 
                       parameter.getParameterType().equals(AuthUser.class);
            }

            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                          NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth == null) return null;
                return AuthUser.builder()
                        .userId(auth.getName())
                        .role("USER") 
                        .build();
            }
        });
    }
}
