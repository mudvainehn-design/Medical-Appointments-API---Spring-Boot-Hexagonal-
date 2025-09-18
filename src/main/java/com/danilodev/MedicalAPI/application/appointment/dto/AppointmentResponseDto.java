package com.danilodev.MedicalAPI.application.appointment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponseDto(
        UUID id,
        String patientName,
        String doctorName,
        LocalDateTime dateTime,
        String status
) {
}
