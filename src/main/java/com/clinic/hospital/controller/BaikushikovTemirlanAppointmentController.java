package com.clinic.hospital.controller;

import com.clinic.hospital.dto.BaikushikovTemirlanAppointmentDto;
import com.clinic.hospital.service.BaikushikovTemirlanAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointments", description = "Appointment management endpoints")
public class BaikushikovTemirlanAppointmentController {

    private final BaikushikovTemirlanAppointmentService appointmentService;

    @GetMapping
    @Operation(summary = "Get all appointments")
    public ResponseEntity<List<BaikushikovTemirlanAppointmentDto>> getAll() {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get appointment by ID")
    public ResponseEntity<BaikushikovTemirlanAppointmentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getById(id));
    }

    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Get appointments by doctor with pagination")
    public ResponseEntity<Page<BaikushikovTemirlanAppointmentDto>> getByDoctor(
            @PathVariable Long doctorId,
            @PageableDefault(size = 10, sort = "appointmentDate") Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getByDoctorId(doctorId, pageable));
    }

    @PostMapping
    @Operation(summary = "Create appointment")
    public ResponseEntity<BaikushikovTemirlanAppointmentDto> create(@Valid @RequestBody BaikushikovTemirlanAppointmentDto dto) {
        return ResponseEntity.ok(appointmentService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update appointment")
    public ResponseEntity<BaikushikovTemirlanAppointmentDto> update(@PathVariable Long id, @Valid @RequestBody BaikushikovTemirlanAppointmentDto dto) {
        return ResponseEntity.ok(appointmentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete appointment")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}