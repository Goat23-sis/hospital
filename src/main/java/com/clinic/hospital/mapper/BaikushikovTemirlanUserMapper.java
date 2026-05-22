package com.clinic.hospital.mapper;

import com.clinic.hospital.dto.BaikushikovTemirlanUserDto;
import com.clinic.hospital.entity.BaikushikovTemirlanUser;
import org.springframework.stereotype.Component;

@Component
public class BaikushikovTemirlanUserMapper {

    public BaikushikovTemirlanUserDto toDto(BaikushikovTemirlanUser user) {
        if (user == null) return null;
        return BaikushikovTemirlanUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public BaikushikovTemirlanUser toEntity(BaikushikovTemirlanUserDto dto) {
        if (dto == null) return null;
        return BaikushikovTemirlanUser.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }
}