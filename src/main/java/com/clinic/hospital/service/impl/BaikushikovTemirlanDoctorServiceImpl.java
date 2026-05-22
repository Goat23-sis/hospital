package com.clinic.hospital.service.impl;

import com.clinic.hospital.dto.BaikushikovTemirlanDoctorDto;
import com.clinic.hospital.entity.BaikushikovTemirlanDepartment;
import com.clinic.hospital.entity.BaikushikovTemirlanDoctor;
import com.clinic.hospital.mapper.BaikushikovTemirlanDoctorMapper;
import com.clinic.hospital.repository.BaikushikovTemirlanDepartmentRepository;
import com.clinic.hospital.repository.BaikushikovTemirlanDoctorRepository;
import com.clinic.hospital.service.BaikushikovTemirlanDoctorService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaikushikovTemirlanDoctorServiceImpl
        implements BaikushikovTemirlanDoctorService {

    private final BaikushikovTemirlanDoctorRepository doctorRepository;

    private final BaikushikovTemirlanDepartmentRepository
            departmentRepository;

    private final BaikushikovTemirlanDoctorMapper doctorMapper;

    @Override
    public BaikushikovTemirlanDoctorDto createDoctor(
            BaikushikovTemirlanDoctorDto dto
    ) {

        BaikushikovTemirlanDoctor doctor =
                doctorMapper.toEntity(dto);

        if (dto.getDepartmentId() != null) {

            BaikushikovTemirlanDepartment department =
                    departmentRepository.findById(
                                    dto.getDepartmentId()
                            )
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Department not found"
                                    ));

            doctor.setDepartment(department);
        }

        BaikushikovTemirlanDoctor savedDoctor =
                doctorRepository.save(doctor);

        return doctorMapper.toDto(savedDoctor);
    }

    @Override
    public BaikushikovTemirlanDoctorDto getDoctorById(Long id) {

        BaikushikovTemirlanDoctor doctor =
                doctorRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Doctor not found"
                                ));

        return doctorMapper.toDto(doctor);
    }

    @Override
    public Page<BaikushikovTemirlanDoctorDto> getAllDoctors(
            String specialization,
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable =
                PageRequest.of(page, size, Sort.by(sortBy));

        Page<BaikushikovTemirlanDoctor> doctors;

        if (specialization != null &&
                !specialization.isEmpty()) {

            doctors =
                    doctorRepository
                            .findBySpecializationContainingIgnoreCase(
                                    specialization,
                                    pageable
                            );

        } else {

            doctors = doctorRepository.findAll(pageable);
        }

        return doctors.map(doctorMapper::toDto);
    }

    @Override
    public BaikushikovTemirlanDoctorDto updateDoctor(
            Long id,
            BaikushikovTemirlanDoctorDto dto
    ) {

        BaikushikovTemirlanDoctor doctor =
                doctorRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Doctor not found"
                                ));

        doctor.setFirstName(dto.getFirstName());
        doctor.setLastName(dto.getLastName());
        doctor.setEmail(dto.getEmail());
        doctor.setPhone(dto.getPhone());
        doctor.setSpecialization(dto.getSpecialization());

        if (dto.getDepartmentId() != null) {

            BaikushikovTemirlanDepartment department =
                    departmentRepository.findById(
                                    dto.getDepartmentId()
                            )
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Department not found"
                                    ));

            doctor.setDepartment(department);
        }

        return doctorMapper.toDto(
                doctorRepository.save(doctor)
        );
    }

    @Override
    public void deleteDoctor(Long id) {

        doctorRepository.deleteById(id);
    }
}