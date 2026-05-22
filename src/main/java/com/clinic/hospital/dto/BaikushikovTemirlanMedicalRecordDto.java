package com.clinic.hospital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaikushikovTemirlanMedicalRecordDto {

    private Long id;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    private String patientName;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    private String doctorName;

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

    private String treatment;

    private String notes;

    private LocalDate recordDate;
}