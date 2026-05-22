package com.clinic.hospital.controller;

import com.clinic.hospital.service.BaikushikovTemirlanFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Tag(name = "Files", description = "File upload and download endpoints")
public class BaikushikovTemirlanFileController {

    private final BaikushikovTemirlanFileService fileService;

    @PostMapping("/upload")
    @Operation(summary = "Upload file")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = fileService.uploadFile(file);
        return ResponseEntity.ok("File uploaded: " + filename);
    }

    @GetMapping("/download/{filename}")
    @Operation(summary = "Download file")
    public ResponseEntity<Resource> download(@PathVariable String filename) throws IOException {
        Resource resource = fileService.downloadFile(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
}