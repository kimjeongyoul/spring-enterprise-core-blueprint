package com.vibe.core.storage;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * [Abstraction] ?ㅽ넗由ъ? ?쒕퉬???명꽣?섏씠??
 * 臾쇰━?곸씤 ??μ냼(S3, FTP, Local)???곴??놁씠 ?쇨????뚯씪 議곗옉??蹂댁옣?⑸땲??
 */
public interface StorageProvider {
    String upload(MultipartFile file, String path);
    void delete(String fileUrl);
    byte[] download(String fileUrl);
    
    default List<String> uploadMultiple(List<MultipartFile> files, String path) {
        return files.stream().map(f -> upload(f, path)).toList();
    }
}

