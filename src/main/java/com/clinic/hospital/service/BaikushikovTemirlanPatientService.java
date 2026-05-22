package com.clinic.hospital.service;

import com.clinic.hospital.dto.BaikushikovTemirlanPatientDto;
import com.clinic.hospital.entity.BaikushikovTemirlanPatient;
import com.clinic.hospital.exception.BaikushikovTemirlanResourceNotFoundException;
import com.clinic.hospital.mapper.BaikushikovTemirlanPatientMapper;
import com.clinic.hospital.repository.BaikushikovTemirlanPatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaikushikovTemirlanPatientService {

    private final BaikushikovTemirlanPatientRepository patientRepository;
    private final BaikushikovTemirlanPatientMapper patientMapper;

    public List<BaikushikovTemirlanPatientDto> getAll() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public BaikushikovTemirlanPatientDto getById(Long id) {
        return patientMapper.toDto(patientRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Patient not found with id: " + id)));
    }

    public List<BaikushikovTemirlanPatientDto> searchByName(String name) {
        return patientRepository.searchByName(name)
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public BaikushikovTemirlanPatientDto create(BaikushikovTemirlanPatientDto dto) {
        log.info("Creating patient: {} {}", dto.getFirstName(), dto.getLastName());
        if (patientRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        BaikushikovTemirlanPatient patient = patientMapper.toEntity(dto);
        return patientMapper.toDto(patientRepository.save(patient));
    }

    public BaikushikovTemirlanPatientDto update(Long id, BaikushikovTemirlanPatientDto dto) {
        BaikushikovTemirlanPatient patient = patientRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Patient not found with id: " + id));
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        patient.setBirthDate(dto.getBirthDate());
        patient.setAddress(dto.getAddress());
        return patientMapper.toDto(patientRepository.save(patient));
    }

    public void delete(Long id) {
        patientRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Patient not found with id: " + id));
        patientRepository.deleteById(id);
    }
}