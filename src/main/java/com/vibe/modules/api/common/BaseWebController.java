package com.vibe.modules.api.common;

import com.vibe.core.dto.ApiErrorResponse;
import com.vibe.core.storage.StorageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * [Standard] 기본 웹 요청 및 파일 처리 템플릿
 */
@RestController
@RequestMapping("/api/v1/common")
@RequiredArgsConstructor
public class BaseWebController {

    private final StorageProvider storageProvider;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        return storageProvider.upload(file, "uploads/common");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleBadRequest(IllegalArgumentException e) {
        return ApiErrorResponse.builder()
                .code("INVALID_REQUEST")
                .message(e.getMessage())
                .build();
    }
}
