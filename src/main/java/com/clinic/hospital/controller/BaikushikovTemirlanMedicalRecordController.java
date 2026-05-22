package com.clinic.hospital.controller;

import com.clinic.hospital.dto.BaikushikovTemirlanMedicalRecordDto;
import com.clinic.hospital.service.BaikushikovTemirlanMedicalRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
@Tag(name = "Medical Records", description = "Medical record management endpoints")
public class BaikushikovTemirlanMedicalRecordController {

    private final BaikushikovTemirlanMedicalRecordService medicalRecordService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @Operation(summary = "Get all medical records")
    public ResponseEntity<List<BaikushikovTemirlanMedicalRecordDto>> getAll() {
        return ResponseEntity.ok(medicalRecordService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @Operation(summary = "Get medical record by ID")
    public ResponseEntity<BaikushikovTemirlanMedicalRecordDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.getById(id));
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @Operation(summary = "Get medical records by patient")
    public ResponseEntity<List<BaikushikovTemirlanMedicalRecordDto>> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(medicalRecordService.getByPatientId(patientId));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @Operation(summary = "Create medical record")
    public ResponseEntity<BaikushikovTemirlanMedicalRecordDto> create(@Valid @RequestBody BaikushikovTemirlanMedicalRecordDto dto) {
        return ResponseEntity.ok(medicalRecordService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @Operation(summary = "Update medical record")
    public ResponseEntity<BaikushikovTemirlanMedicalRecordDto> update(@PathVariable Long id, @Valid @RequestBody BaikushikovTemirlanMedicalRecordDto dto) {
        return ResponseEntity.ok(medicalRecordService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete medical record")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicalRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }
}