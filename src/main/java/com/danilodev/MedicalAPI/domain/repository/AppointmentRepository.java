package com.danilodev.MedicalAPI.domain.repository;

import com.danilodev.MedicalAPI.domain.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    Optional<Appointment> findById(UUID id);
    List<Appointment> findByDoctorAndDate(UUID doctorId, LocalDateTime dateTime);
}
