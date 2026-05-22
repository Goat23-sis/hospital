package com.clinic.hospital.repository;

import com.clinic.hospital.entity.BaikushikovTemirlanAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BaikushikovTemirlanAppointmentRepository extends JpaRepository<BaikushikovTemirlanAppointment, Long> {
    List<BaikushikovTemirlanAppointment> findByDoctorId(Long doctorId);
    List<BaikushikovTemirlanAppointment> findByPatientId(Long patientId);
    List<BaikushikovTemirlanAppointment> findByStatus(BaikushikovTemirlanAppointment.Status status);

    Page<BaikushikovTemirlanAppointment> findByDoctorId(Long doctorId, Pageable pageable);

    @Query("SELECT a FROM BaikushikovTemirlanAppointment a WHERE " +
            "a.appointmentDate BETWEEN :start AND :end")
    List<BaikushikovTemirlanAppointment> findByDateRange(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}