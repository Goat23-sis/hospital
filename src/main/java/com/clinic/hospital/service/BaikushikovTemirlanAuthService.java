package com.clinic.hospital.service;

import com.clinic.hospital.dto.BaikushikovTemirlanUserDto;
import com.clinic.hospital.entity.BaikushikovTemirlanUser;
import com.clinic.hospital.repository.BaikushikovTemirlanUserRepository;
import com.clinic.hospital.security.BaikushikovTemirlanJwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BaikushikovTemirlanAuthService {

    private final BaikushikovTemirlanUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BaikushikovTemirlanJwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public Map<String, String> register(BaikushikovTemirlanUserDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        BaikushikovTemirlanUser user = BaikushikovTemirlanUser.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole() != null ? dto.getRole() : BaikushikovTemirlanUser.Role.PATIENT)
                .build();
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("username", user.getUsername());
        return response;
    }

    public Map<String, String> login(BaikushikovTemirlanUserDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        String token = jwtUtil.generateToken(dto.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("username", dto.getUsername());
        return response;
    }
}