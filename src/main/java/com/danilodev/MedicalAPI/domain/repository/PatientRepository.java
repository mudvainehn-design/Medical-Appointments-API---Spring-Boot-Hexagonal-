package com.danilodev.MedicalAPI.domain.repository;

import com.danilodev.MedicalAPI.domain.model.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository {
    Patient save(Patient patient);
    Optional<Patient> findById(UUID id);
    List<Patient> findAll();
}
