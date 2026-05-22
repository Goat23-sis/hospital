package com.clinic.hospital.service;

import com.clinic.hospital.dto.BaikushikovTemirlanDoctorDto;
import org.springframework.data.domain.Page;

public interface BaikushikovTemirlanDoctorService {

    BaikushikovTemirlanDoctorDto createDoctor(
            BaikushikovTemirlanDoctorDto dto
    );

    BaikushikovTemirlanDoctorDto getDoctorById(Long id);

    Page<BaikushikovTemirlanDoctorDto> getAllDoctors(
            String specialization,
            int page,
            int size,
            String sortBy
    );

    BaikushikovTemirlanDoctorDto updateDoctor(
            Long id,
            BaikushikovTemirlanDoctorDto dto
    );

    void deleteDoctor(Long id);
}