package com.clinic.hospital.mapper;

import com.clinic.hospital.dto.BaikushikovTemirlanPatientDto;
import com.clinic.hospital.entity.BaikushikovTemirlanPatient;
import org.springframework.stereotype.Component;

@Component
public class BaikushikovTemirlanPatientMapper {

    public BaikushikovTemirlanPatientDto toDto(BaikushikovTemirlanPatient patient) {
        if (patient == null) return null;
        return BaikushikovTemirlanPatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .birthDate(patient.getBirthDate())
                .address(patient.getAddress())
                .build();
    }

    public BaikushikovTemirlanPatient toEntity(BaikushikovTemirlanPatientDto dto) {
        if (dto == null) return null;
        return BaikushikovTemirlanPatient.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .birthDate(dto.getBirthDate())
                .address(dto.getAddress())
                .build();
    }
}