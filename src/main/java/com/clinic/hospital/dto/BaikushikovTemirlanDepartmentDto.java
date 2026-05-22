package com.clinic.hospital.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaikushikovTemirlanDepartmentDto {

    private Long id;

    @NotBlank(message = "Department name is required")
    private String name;

    private String description;
}