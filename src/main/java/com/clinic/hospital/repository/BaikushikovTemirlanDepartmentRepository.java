package com.clinic.hospital.repository;

import com.clinic.hospital.entity.BaikushikovTemirlanDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BaikushikovTemirlanDepartmentRepository extends JpaRepository<BaikushikovTemirlanDepartment, Long> {
    Optional<BaikushikovTemirlanDepartment> findByName(String name);
    boolean existsByName(String name);
}