package com.clinic.hospital.controller;

import com.clinic.hospital.dto.BaikushikovTemirlanAuthRequest;
import com.clinic.hospital.dto.BaikushikovTemirlanAuthResponse;
import com.clinic.hospital.dto.BaikushikovTemirlanUserDto;
import com.clinic.hospital.entity.BaikushikovTemirlanUser;
import com.clinic.hospital.repository.BaikushikovTemirlanUserRepository;
import com.clinic.hospital.security.BaikushikovTemirlanJwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class BaikushikovTemirlanAuthController {

    private final AuthenticationManager authenticationManager;
    private final BaikushikovTemirlanJwtUtil jwtUtil;
    private final BaikushikovTemirlanUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody BaikushikovTemirlanUserDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        BaikushikovTemirlanUser user = BaikushikovTemirlanUser.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole() != null ? dto.getRole() : BaikushikovTemirlanUser.Role.PATIENT)
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<BaikushikovTemirlanAuthResponse> login(
            @Valid @RequestBody BaikushikovTemirlanAuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        BaikushikovTemirlanUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return ResponseEntity.ok(BaikushikovTemirlanAuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build());
    }
}