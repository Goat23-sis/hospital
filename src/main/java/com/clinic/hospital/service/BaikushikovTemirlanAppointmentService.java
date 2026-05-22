package com.clinic.hospital.service;

import com.clinic.hospital.dto.BaikushikovTemirlanAppointmentDto;
import com.clinic.hospital.entity.BaikushikovTemirlanAppointment;
import com.clinic.hospital.entity.BaikushikovTemirlanDoctor;
import com.clinic.hospital.entity.BaikushikovTemirlanPatient;
import com.clinic.hospital.exception.BaikushikovTemirlanResourceNotFoundException;
import com.clinic.hospital.mapper.BaikushikovTemirlanAppointmentMapper;
import com.clinic.hospital.repository.BaikushikovTemirlanAppointmentRepository;
import com.clinic.hospital.repository.BaikushikovTemirlanDoctorRepository;
import com.clinic.hospital.repository.BaikushikovTemirlanPatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaikushikovTemirlanAppointmentService {

    private final BaikushikovTemirlanAppointmentRepository appointmentRepository;
    private final BaikushikovTemirlanDoctorRepository doctorRepository;
    private final BaikushikovTemirlanPatientRepository patientRepository;
    private final BaikushikovTemirlanAppointmentMapper appointmentMapper;

    public List<BaikushikovTemirlanAppointmentDto> getAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<BaikushikovTemirlanAppointmentDto> getByDoctorId(Long doctorId, Pageable pageable) {
        return appointmentRepository.findByDoctorId(doctorId, pageable)
                .map(appointmentMapper::toDto);
    }

    public BaikushikovTemirlanAppointmentDto getById(Long id) {
        return appointmentMapper.toDto(appointmentRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Appointment not found with id: " + id)));
    }

    public BaikushikovTemirlanAppointmentDto create(BaikushikovTemirlanAppointmentDto dto) {
        BaikushikovTemirlanDoctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Doctor not found"));
        BaikushikovTemirlanPatient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Patient not found"));
        BaikushikovTemirlanAppointment appointment = appointmentMapper.toEntity(dto);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setStatus(BaikushikovTemirlanAppointment.Status.SCHEDULED);
        sendAppointmentNotification(dto);
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    public BaikushikovTemirlanAppointmentDto update(Long id, BaikushikovTemirlanAppointmentDto dto) {
        BaikushikovTemirlanAppointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Appointment not found with id: " + id));
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setReason(dto.getReason());
        appointment.setStatus(dto.getStatus());
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    public void delete(Long id) {
        appointmentRepository.findById(id)
                .orElseThrow(() -> new BaikushikovTemirlanResourceNotFoundException("Appointment not found with id: " + id));
        appointmentRepository.deleteById(id);
    }

    @Async
    public CompletableFuture<Void> sendAppointmentNotification(BaikushikovTemirlanAppointmentDto dto) {
        log.info("Sending notification for appointment: doctorId={}, patientId={}", dto.getDoctorId(), dto.getPatientId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("Notification sent successfully");
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<List<BaikushikovTemirlanAppointmentDto>> getAppointmentsAsync(Long doctorId) {
        log.info("Fetching appointments async for doctorId={}", doctorId);
        List<BaikushikovTemirlanAppointmentDto> appointments = appointmentRepository
                .findByDoctorId(doctorId)
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(appointments);
    }
}