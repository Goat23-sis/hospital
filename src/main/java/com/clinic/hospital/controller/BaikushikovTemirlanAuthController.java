package com.clinic.hospital.controller;

import com.clinic.hospital.dto.BaikushikovTemirlanUserDto;
import com.clinic.hospital.service.BaikushikovTemirlanAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Registration and login endpoints")
public class BaikushikovTemirlanAuthController {

    private final BaikushikovTemirlanAuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register new user")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody BaikushikovTemirlanUserDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody BaikushikovTemirlanUserDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}