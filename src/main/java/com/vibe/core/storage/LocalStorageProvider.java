package com.vibe.core.storage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

@Component
public class LocalStorageProvider implements StorageProvider {

    private final Path root = Paths.get("uploads");

    @Override
    public String upload(MultipartFile file, String path) {
        try {
            Files.createDirectories(root.resolve(path));
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.root.resolve(path).resolve(fileName));
            return path + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(String fileUrl) { /* 구현 생략 */ }

    @Override
    public byte[] download(String fileUrl) { return new byte[0]; }
}
