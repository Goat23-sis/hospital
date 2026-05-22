package com.clinic.hospital.dto;

import com.clinic.hospital.entity.BaikushikovTemirlanAppointment;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaikushikovTemirlanAppointmentDto {

    private Long id;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    private String doctorName;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    private String patientName;

    @NotNull(message = "Appointment date is required")
    private LocalDateTime appointmentDate;

    private String reason;

    private BaikushikovTemirlanAppointment.Status status;
}