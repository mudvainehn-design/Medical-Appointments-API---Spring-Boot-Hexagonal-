package com.danilodev.MedicalAPI.domain.repository;

import com.danilodev.MedicalAPI.domain.model.Doctor;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository {
    Doctor save(Doctor doctor);
    Optional<Doctor> findById(UUID id);
    List<Doctor> findAll();
}
