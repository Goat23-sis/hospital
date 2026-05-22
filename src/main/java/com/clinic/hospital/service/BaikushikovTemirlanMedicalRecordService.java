package com.clinic.hospital.service;

import com.clinic.hospital.dto.BaikushikovTemirlanMedicalRecordDto;
import com.clinic.hospital.entity.BaikushikovTemirlanMedicalRecord;
import com.clinic.hospital.entity.BaikushikovTemirlanDoctor;
import com.clinic.hospital.entity.BaikushikovTemirlanPatient;
import com.clinic.hospital.exception.BaikushikovTemirlanResourceNotFoundException;
import com.clinic.hospital.mapper.BaikushikovTemirlanMedicalRecordMapper;
import com.clinic.hospital.repository.BaikushikovTemirlanMedicalRecordRepository;
import com.clinic.hospital.repository.BaikushikovTemirlanDoctorRepository;
import com.clinic.hospital.repository.BaikushikovTemirlanPatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaikushikovTemirlanMedicalRecordService {

    private final BaikushikovTemirlanMedicalRecordRepository medicalRecordRepository;
    private final BaikushikovTemirlanDoctorRepository doctorRepository;
    private final BaikushikovTemirlanPatientRepository patientRepository;
    private final BaikushikovTemirlanMedicalRecordMapper medicalRecordMapper;

    public List<BaikushikovTemirlanMedicalRecordDto> getAll() {
        return medicalRecordRepository.findAll()
                .stream()
                .map(medicalRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    public BaikushikovTemirlanMedicalRecordDto getById(Long id) {
        return medicalRecordMapper.toDto(medicalRecordRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Medical record not found with id: " + id)));
    }

    public List<BaikushikovTemirlanMedicalRecordDto> getByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId)
                .stream()
                .map(medicalRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    public BaikushikovTemirlanMedicalRecordDto create(BaikushikovTemirlanMedicalRecordDto dto) {
        log.info("Creating medical record for patientId={}", dto.getPatientId());
        BaikushikovTemirlanDoctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Doctor not found"));
        BaikushikovTemirlanPatient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Patient not found"));
        BaikushikovTemirlanMedicalRecord record = medicalRecordMapper.toEntity(dto);
        record.setDoctor(doctor);
        record.setPatient(patient);
        record.setRecordDate(LocalDate.now());
        return medicalRecordMapper.toDto(medicalRecordRepository.save(record));
    }

    public BaikushikovTemirlanMedicalRecordDto update(Long id, BaikushikovTemirlanMedicalRecordDto dto) {
        BaikushikovTemirlanMedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Medical record not found with id: " + id));
        record.setDiagnosis(dto.getDiagnosis());
        record.setTreatment(dto.getTreatment());
        record.setNotes(dto.getNotes());
        return medicalRecordMapper.toDto(medicalRecordRepository.save(record));
    }

    public void delete(Long id) {
        medicalRecordRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Medical record not found with id: " + id));
        medicalRecordRepository.deleteById(id);
    }

    @Async
    public CompletableFuture<List<BaikushikovTemirlanMedicalRecordDto>> getRecordsAsync(Long patientId) {
        log.info("Fetching medical records async for patientId={}", patientId);
        List<BaikushikovTemirlanMedicalRecordDto> records = medicalRecordRepository
                .findByPatientId(patientId)
                .stream()
                .map(medicalRecordMapper::toDto)
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(records);
    }
}