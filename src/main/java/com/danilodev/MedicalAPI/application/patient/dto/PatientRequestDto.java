package com.danilodev.MedicalAPI.application.patient.dto;

import java.time.LocalDate;

public record PatientRequestDto(
        String name,
        String email,
        LocalDate birthdate
) {
}
