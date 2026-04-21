package com.vibe.core.storage;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * [Abstraction] 스토리지 서비스 인터페이스
 * 물리적인 저장소(S3, FTP, Local)에 상관없이 일관된 파일 조작을 보장합니다.
 */
public interface StorageProvider {
    String upload(MultipartFile file, String path);
    void delete(String fileUrl);
    byte[] download(String fileUrl);
    
    default List<String> uploadMultiple(List<MultipartFile> files, String path) {
        return files.stream().map(f -> upload(f, path)).toList();
    }
}

