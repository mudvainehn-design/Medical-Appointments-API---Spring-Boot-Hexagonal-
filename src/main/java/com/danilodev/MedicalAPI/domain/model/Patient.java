package com.danilodev.MedicalAPI.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Patient {
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private int missedAppointments;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
