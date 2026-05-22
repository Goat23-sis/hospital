package com.clinic.hospital.controller;

import com.clinic.hospital.dto.BaikushikovTemirlanDoctorDto;
import com.clinic.hospital.service.BaikushikovTemirlanDoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
@Tag(name = "Doctors", description = "Doctor management endpoints")
public class BaikushikovTemirlanDoctorController {

    private final BaikushikovTemirlanDoctorService doctorService;

    @GetMapping
    @Operation(summary = "Get all doctors")
    public ResponseEntity<List<BaikushikovTemirlanDoctorDto>> getAll() {
        return ResponseEntity.ok(doctorService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get doctor by ID")
    public ResponseEntity<BaikushikovTemirlanDoctorDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search doctors by name")
    public ResponseEntity<List<BaikushikovTemirlanDoctorDto>> search(@RequestParam String name) {
        return ResponseEntity.ok(doctorService.searchByName(name));
    }

    @GetMapping("/specialization")
    @Operation(summary = "Get doctors by specialization")
    public ResponseEntity<List<BaikushikovTemirlanDoctorDto>> getBySpecialization(@RequestParam String specialization) {
        return ResponseEntity.ok(doctorService.getBySpecialization(specialization));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create doctor")
    public ResponseEntity<BaikushikovTemirlanDoctorDto> create(@Valid @RequestBody BaikushikovTemirlanDoctorDto dto) {
        return ResponseEntity.ok(doctorService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update doctor")
    public ResponseEntity<BaikushikovTemirlanDoctorDto> update(@PathVariable Long id, @Valid @RequestBody BaikushikovTemirlanDoctorDto dto) {
        return ResponseEntity.ok(doctorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete doctor")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}