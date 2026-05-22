package com.clinic.hospital.mapper;

import com.clinic.hospital.dto.BaikushikovTemirlanDoctorDto;
import com.clinic.hospital.entity.BaikushikovTemirlanDoctor;
import org.springframework.stereotype.Component;

@Component
public class BaikushikovTemirlanDoctorMapper {

    public BaikushikovTemirlanDoctorDto toDto(BaikushikovTemirlanDoctor doctor) {
        if (doctor == null) return null;
        return BaikushikovTemirlanDoctorDto.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .email(doctor.getEmail())
                .phone(doctor.getPhone())
                .specialization(doctor.getSpecialization())
                .departmentId(doctor.getDepartment() != null ? doctor.getDepartment().getId() : null)
                .departmentName(doctor.getDepartment() != null ? doctor.getDepartment().getName() : null)
                .build();
    }

    public BaikushikovTemirlanDoctor toEntity(BaikushikovTemirlanDoctorDto dto) {
        if (dto == null) return null;
        return BaikushikovTemirlanDoctor.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .specialization(dto.getSpecialization())
                .build();
    }
}