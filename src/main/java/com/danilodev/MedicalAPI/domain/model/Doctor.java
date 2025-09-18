package com.danilodev.MedicalAPI.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Doctor {
    private Long  id;
    private String name;
    private String speciality;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
