package com.clinic.hospital.repository;

import com.clinic.hospital.entity.BaikushikovTemirlanPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BaikushikovTemirlanPatientRepository extends JpaRepository<BaikushikovTemirlanPatient, Long> {
    Optional<BaikushikovTemirlanPatient> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT p FROM BaikushikovTemirlanPatient p WHERE " +
            "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
            "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<BaikushikovTemirlanPatient> searchByName(@Param("name") String name);
}