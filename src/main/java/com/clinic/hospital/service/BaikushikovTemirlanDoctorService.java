package com.clinic.hospital.service;

import com.clinic.hospital.dto.BaikushikovTemirlanDoctorDto;
import com.clinic.hospital.entity.BaikushikovTemirlanDoctor;
import com.clinic.hospital.entity.BaikushikovTemirlanDepartment;
import com.clinic.hospital.exception.BaikushikovTemirlanResourceNotFoundException;
import com.clinic.hospital.mapper.BaikushikovTemirlanDoctorMapper;
import com.clinic.hospital.repository.BaikushikovTemirlanDoctorRepository;
import com.clinic.hospital.repository.BaikushikovTemirlanDepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaikushikovTemirlanDoctorService {

    private final BaikushikovTemirlanDoctorRepository doctorRepository;
    private final BaikushikovTemirlanDepartmentRepository departmentRepository;
    private final BaikushikovTemirlanDoctorMapper doctorMapper;

    public List<BaikushikovTemirlanDoctorDto> getAll() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public BaikushikovTemirlanDoctorDto getById(Long id) {
        return doctorMapper.toDto(doctorRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Doctor not found with id: " + id)));
    }

    public List<BaikushikovTemirlanDoctorDto> searchByName(String name) {
        return doctorRepository.searchByName(name)
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BaikushikovTemirlanDoctorDto> getBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization)
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public BaikushikovTemirlanDoctorDto create(BaikushikovTemirlanDoctorDto dto) {
        log.info("Creating doctor: {} {}", dto.getFirstName(), dto.getLastName());
        BaikushikovTemirlanDoctor doctor = doctorMapper.toEntity(dto);
        if (dto.getDepartmentId() != null) {
            BaikushikovTemirlanDepartment department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Department not found"));
            doctor.setDepartment(department);
        }
        return doctorMapper.toDto(doctorRepository.save(doctor));
    }

    public BaikushikovTemirlanDoctorDto update(Long id, BaikushikovTemirlanDoctorDto dto) {
        BaikushikovTemirlanDoctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Doctor not found with id: " + id));
        doctor.setFirstName(dto.getFirstName());
        doctor.setLastName(dto.getLastName());
        doctor.setEmail(dto.getEmail());
        doctor.setPhone(dto.getPhone());
        doctor.setSpecialization(dto.getSpecialization());
        if (dto.getDepartmentId() != null) {
            BaikushikovTemirlanDepartment department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Department not found"));
            doctor.setDepartment(department);
        }
        return doctorMapper.toDto(doctorRepository.save(doctor));
    }

    public void delete(Long id) {
        doctorRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Doctor not found with id: " + id));
        doctorRepository.deleteById(id);
    }
}