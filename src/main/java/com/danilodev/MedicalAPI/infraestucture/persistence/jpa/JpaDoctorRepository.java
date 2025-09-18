package com.danilodev.MedicalAPI.infraestucture.persistence.jpa;

import com.danilodev.MedicalAPI.infraestucture.persistence.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaDoctorRepository extends JpaRepository<DoctorEntity, UUID> {
}
