package com.vibe.core.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private T data;
    private ApiError error;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("SUCCESS", data, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> failure(String code, String message) {
        return new ApiResponse<>("FAILURE", null, new ApiError(code, message), LocalDateTime.now());
    }

    @Getter
    @AllArgsConstructor
    public static class ApiError {
        private String code;
        private String message;
    }
}

