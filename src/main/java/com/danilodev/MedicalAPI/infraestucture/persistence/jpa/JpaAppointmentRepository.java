package com.danilodev.MedicalAPI.infraestucture.persistence.jpa;

import com.danilodev.MedicalAPI.infraestucture.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaAppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {
}
