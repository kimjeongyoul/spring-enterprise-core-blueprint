package com.vibe.modules.common.api.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * [Standard] 공통 페이징 응답 포맷
 * 스프링의 Page 객체를 그대로 반환하지 않고, 필요한 정보만 정규화하여 반환합니다.
 */
@Getter
public class PageResponse<T> {
    private final List<T> content;
    private final int pageNumber;
    private final int pageSize;
    private final long totalElements;
    private final int totalPages;
    private final boolean last;

    public PageResponse(Page<T> page) {
        this.content = page.getContent();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.last = page.isLast();
    }

    public static <T> PageResponse<T> of(Page<T> page) {
        return new PageResponse<>(page);
    }
}
