package com.clinic.hospital.controller;

import com.clinic.hospital.dto.BaikushikovTemirlanPatientDto;
import com.clinic.hospital.service.BaikushikovTemirlanPatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@Tag(name = "Patients", description = "Patient management endpoints")
public class BaikushikovTemirlanPatientController {

    private final BaikushikovTemirlanPatientService patientService;

    @GetMapping
    @Operation(summary = "Get all patients")
    public ResponseEntity<List<BaikushikovTemirlanPatientDto>> getAll() {
        return ResponseEntity.ok(patientService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get patient by ID")
    public ResponseEntity<BaikushikovTemirlanPatientDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search patients by name")
    public ResponseEntity<List<BaikushikovTemirlanPatientDto>> search(@RequestParam String name) {
        return ResponseEntity.ok(patientService.searchByName(name));
    }

    @PostMapping
    @Operation(summary = "Create patient")
    public ResponseEntity<BaikushikovTemirlanPatientDto> create(@Valid @RequestBody BaikushikovTemirlanPatientDto dto) {
        return ResponseEntity.ok(patientService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @Operation(summary = "Update patient")
    public ResponseEntity<BaikushikovTemirlanPatientDto> update(@PathVariable Long id, @Valid @RequestBody BaikushikovTemirlanPatientDto dto) {
        return ResponseEntity.ok(patientService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete patient")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}