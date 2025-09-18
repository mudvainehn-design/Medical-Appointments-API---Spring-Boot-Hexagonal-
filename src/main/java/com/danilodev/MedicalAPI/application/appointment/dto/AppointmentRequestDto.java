package com.danilodev.MedicalAPI.application.appointment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentRequestDto(
        UUID patientId,
        UUID doctorId,
        LocalDateTime dateTime
) {
}
