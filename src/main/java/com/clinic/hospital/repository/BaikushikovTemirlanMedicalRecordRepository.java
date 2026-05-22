package com.clinic.hospital.repository;

import com.clinic.hospital.entity.BaikushikovTemirlanMedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BaikushikovTemirlanMedicalRecordRepository extends JpaRepository<BaikushikovTemirlanMedicalRecord, Long> {
    List<BaikushikovTemirlanMedicalRecord> findByPatientId(Long patientId);
    List<BaikushikovTemirlanMedicalRecord> findByDoctorId(Long doctorId);
}