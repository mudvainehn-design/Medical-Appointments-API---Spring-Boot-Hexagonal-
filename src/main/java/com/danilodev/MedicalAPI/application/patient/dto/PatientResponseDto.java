package com.danilodev.MedicalAPI.application.patient.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PatientResponseDto(
        UUID id,
        String name,
        String email,
        LocalDate birthDate
) {
}
