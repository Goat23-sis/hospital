package com.clinic.hospital.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class BaikushikovTemirlanFileService {

    private final Path uploadDir = Paths.get("uploads");

    public BaikushikovTemirlanFileService() throws IOException {
        Files.createDirectories(uploadDir);
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        log.info("File uploaded: {}", filename);
        return filename;
    }

    public Resource downloadFile(String filename) throws MalformedURLException {
        Path filePath = uploadDir.resolve(filename);
        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists()) {
            throw new RuntimeException("File not found: " + filename);
        }
        return resource;
    }

    @Async
    public CompletableFuture<String> uploadFileAsync(MultipartFile file) throws IOException {
        String filename = uploadFile(file);
        log.info("Async file upload completed: {}", filename);
        return CompletableFuture.completedFuture(filename);
    }
}