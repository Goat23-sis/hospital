package com.clinic.hospital.mapper;

import com.clinic.hospital.dto.BaikushikovTemirlanDepartmentDto;
import com.clinic.hospital.entity.BaikushikovTemirlanDepartment;
import org.springframework.stereotype.Component;

@Component
public class BaikushikovTemirlanDepartmentMapper {

    public BaikushikovTemirlanDepartmentDto toDto(BaikushikovTemirlanDepartment department) {
        if (department == null) return null;
        return BaikushikovTemirlanDepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }

    public BaikushikovTemirlanDepartment toEntity(BaikushikovTemirlanDepartmentDto dto) {
        if (dto == null) return null;
        return BaikushikovTemirlanDepartment.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}