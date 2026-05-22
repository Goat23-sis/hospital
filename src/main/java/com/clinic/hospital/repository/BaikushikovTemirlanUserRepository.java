package com.clinic.hospital.repository;

import com.clinic.hospital.entity.BaikushikovTemirlanUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BaikushikovTemirlanUserRepository extends JpaRepository<BaikushikovTemirlanUser, Long> {
    Optional<BaikushikovTemirlanUser> findByUsername(String username);
    Optional<BaikushikovTemirlanUser> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}