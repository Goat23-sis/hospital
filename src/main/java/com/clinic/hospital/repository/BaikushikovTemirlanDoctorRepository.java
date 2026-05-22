package com.clinic.hospital.repository;

import com.clinic.hospital.entity.BaikushikovTemirlanDoctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaikushikovTemirlanDoctorRepository
        extends JpaRepository<BaikushikovTemirlanDoctor, Long> {

    Optional<BaikushikovTemirlanDoctor> findByEmail(
            String email
    );

    List<BaikushikovTemirlanDoctor> findBySpecialization(
            String specialization
    );

    List<BaikushikovTemirlanDoctor> findByDepartmentId(
            Long departmentId
    );

    Page<BaikushikovTemirlanDoctor>
    findBySpecializationContainingIgnoreCase(
            String specialization,
            Pageable pageable
    );

    @Query("""
            SELECT d
            FROM BaikushikovTemirlanDoctor d
            WHERE LOWER(d.firstName)
            LIKE LOWER(CONCAT('%', :name, '%'))

            OR LOWER(d.lastName)
            LIKE LOWER(CONCAT('%', :name, '%'))
            """)
    List<BaikushikovTemirlanDoctor> searchByName(
            @Param("name") String name
    );
}