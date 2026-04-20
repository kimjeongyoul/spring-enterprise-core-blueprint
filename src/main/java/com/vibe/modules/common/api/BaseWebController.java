package com.vibe.modules.common.api;

import com.vibe.core.dto.ApiErrorResponse;
import com.vibe.core.storage.StorageProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Common", description = "공통 유틸리티 API")
@Slf4j
@RestController
@RequestMapping("/api/v1/public/common") // 테스트 편의를 위해 일단 public으로 이동
@RequiredArgsConstructor
public class BaseWebController {

    private final StorageProvider storageProvider;

    @Operation(summary = "단일 파일 업로드 테스트")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String handleFileUpload(
            @Parameter(description = "업로드할 파일", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @RequestPart("file") MultipartFile file) {
        
        log.info("[API] 파일 업로드 요청 수신: {}", file.getOriginalFilename());
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
