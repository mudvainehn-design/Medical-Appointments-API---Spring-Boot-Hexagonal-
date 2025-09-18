package com.danilodev.MedicalAPI.infraestucture.persistence.jpa;

import com.danilodev.MedicalAPI.infraestucture.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaPatientRepository extends JpaRepository<PatientEntity, UUID> {
}
