package com.clinic.hospital.mapper;

import com.clinic.hospital.dto.BaikushikovTemirlanAppointmentDto;
import com.clinic.hospital.entity.BaikushikovTemirlanAppointment;
import org.springframework.stereotype.Component;

@Component
public class BaikushikovTemirlanAppointmentMapper {

    public BaikushikovTemirlanAppointmentDto toDto(BaikushikovTemirlanAppointment appointment) {
        if (appointment == null) return null;
        return BaikushikovTemirlanAppointmentDto.builder()
                .id(appointment.getId())
                .doctorId(appointment.getDoctor() != null ? appointment.getDoctor().getId() : null)
                .doctorName(appointment.getDoctor() != null ?
                        appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName() : null)
                .patientId(appointment.getPatient() != null ? appointment.getPatient().getId() : null)
                .patientName(appointment.getPatient() != null ?
                        appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName() : null)
                .appointmentDate(appointment.getAppointmentDate())
                .reason(appointment.getReason())
                .status(appointment.getStatus())
                .build();
    }

    public BaikushikovTemirlanAppointment toEntity(BaikushikovTemirlanAppointmentDto dto) {
        if (dto == null) return null;
        return BaikushikovTemirlanAppointment.builder()
                .id(dto.getId())
                .appointmentDate(dto.getAppointmentDate())
                .reason(dto.getReason())
                .status(dto.getStatus())
                .build();
    }
}