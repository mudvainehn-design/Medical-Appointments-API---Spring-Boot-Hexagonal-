package com.danilodev.MedicalAPI.domain.model;

import com.danilodev.MedicalAPI.domain.model.enums.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Appointment {
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate dateTime;
    private AppointmentStatus status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
