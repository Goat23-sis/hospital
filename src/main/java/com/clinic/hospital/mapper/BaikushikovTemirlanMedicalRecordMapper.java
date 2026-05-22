package com.clinic.hospital.mapper;

import com.clinic.hospital.dto.BaikushikovTemirlanMedicalRecordDto;
import com.clinic.hospital.entity.BaikushikovTemirlanMedicalRecord;
import org.springframework.stereotype.Component;

@Component
public class BaikushikovTemirlanMedicalRecordMapper {

    public BaikushikovTemirlanMedicalRecordDto toDto(BaikushikovTemirlanMedicalRecord record) {
        if (record == null) return null;
        return BaikushikovTemirlanMedicalRecordDto.builder()
                .id(record.getId())
                .patientId(record.getPatient() != null ? record.getPatient().getId() : null)
                .patientName(record.getPatient() != null ?
                        record.getPatient().getFirstName() + " " + record.getPatient().getLastName() : null)
                .doctorId(record.getDoctor() != null ? record.getDoctor().getId() : null)
                .doctorName(record.getDoctor() != null ?
                        record.getDoctor().getFirstName() + " " + record.getDoctor().getLastName() : null)
                .diagnosis(record.getDiagnosis())
                .treatment(record.getTreatment())
                .notes(record.getNotes())
                .recordDate(record.getRecordDate())
                .build();
    }

    public BaikushikovTemirlanMedicalRecord toEntity(BaikushikovTemirlanMedicalRecordDto dto) {
        if (dto == null) return null;
        return BaikushikovTemirlanMedicalRecord.builder()
                .id(dto.getId())
                .diagnosis(dto.getDiagnosis())
                .treatment(dto.getTreatment())
                .notes(dto.getNotes())
                .recordDate(dto.getRecordDate())
                .build();
    }
}