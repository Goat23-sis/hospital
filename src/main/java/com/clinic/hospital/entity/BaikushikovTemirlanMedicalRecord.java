package com.clinic.hospital.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaikushikovTemirlanMedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private BaikushikovTemirlanPatient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private BaikushikovTemirlanDoctor doctor;

    @Column(nullable = false)
    private String diagnosis;

    @Column
    private String treatment;

    @Column
    private String notes;

    @Column(nullable = false)
    private LocalDate recordDate;
}