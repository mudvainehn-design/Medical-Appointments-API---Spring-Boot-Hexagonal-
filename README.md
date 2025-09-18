# 🏥 Medical Appointments API - Spring Boot (Hexagonal)

API de gestión de citas médicas desarrollada en **Spring Boot** siguiendo la **arquitectura hexagonal**.  
Incluye integración con **MySQL (Docker)**, **JWT Security**, **Swagger/OpenAPI**, **IA básica para predicción de no-shows**, y exposición vía **MCP + n8n** para orquestación.

---

## 🚀 Características principales

- 📌 Registro de **pacientes** y **doctores**.
- 📅 Agenda dinámica con validación de disponibilidad.
- 🔐 Seguridad con **Spring Security + JWT**.
- 📊 Documentación con **Swagger/OpenAPI**.
- ✉️ Recordatorios automáticos por correo con **Spring Mail**.
- 🤖 **IA integrada** para predecir la probabilidad de ausencias en citas médicas.
- 🔗 Integración con **MCP (Model Context Protocol)** y **n8n** para orquestación.
- 🐘 **Base de datos PostgreSQL** ejecutada en **Docker**.
- 🖥️ Cliente SQL para explorar la base de datos.

---



## 📂 Estructura del proyecto (Arquitectura Hexagonal)

```plaintext
medical-appointments-api/
│── docs/
│── config/
│
├── src/main/java/com/clinic/
│   ├── application/                        # Casos de uso + DTOs
│   │   ├── appointment/
│   │   │   ├── dto/
│   │   │   │   ├── AppointmentRequestDto.java
│   │   │   │   ├── AppointmentResponseDto.java
│   │   │   ├── ScheduleAppointmentUseCase.java
│   │   │   ├── CancelAppointmentUseCase.java
│   │   │   ├── PredictNoShowUseCase.java
│   │   │
│   │   └── patient/
│   │       ├── dto/
│   │       │   ├── PatientRequestDto.java
│   │       │   ├── PatientResponseDto.java
│   │       ├── RegisterPatientUseCase.java
│   │       ├── GetPatientHistoryUseCase.java
│   │
│   ├── domain/                             # Entidades + Interfaces
│   │   ├── model/
│   │   │   ├── Patient.java
│   │   │   ├── Doctor.java
│   │   │   ├── Appointment.java
│   │   │   └── enums/
│   │   │       └── AppointmentStatus.java
│   │   └── repository/
│   │       ├── PatientRepository.java
│   │       ├── DoctorRepository.java
│   │       ├── AppointmentRepository.java
│   │
│   ├── infrastructure/                     # Adaptadores externos
│   │   ├── config/                         # JWT, Swagger, Security
│   │   ├── persistence/
│   │   │   ├── entity/
│   │   │   │   ├── PatientEntity.java
│   │   │   │   ├── DoctorEntity.java
│   │   │   │   ├── AppointmentEntity.java
│   │   │   └── jpa/
│   │   │       ├── JpaPatientRepository.java
│   │   │       ├── JpaDoctorRepository.java
│   │   │       ├── JpaAppointmentRepository.java
│   │   ├── rest/
│   │   │   ├── PatientController.java
│   │   │   ├── DoctorController.java
│   │   │   ├── AppointmentController.java
│   │   ├── mail/
│   │   │   └── ReminderService.java
│   │   ├── ai/
│   │   │   └── NoShowPredictionService.java
│   │
│   └── MedicalAppointmentsApplication.java
│
├── src/main/resources/
│   ├── application.yml
│   └── data.sql
│
└── pom.xml
```

## 📌 Relación entre tablas
```
┌─────────────────────────┐
│       patients          │
│─────────────────────────│
│ id (UUID) PK            │
│ name                    │
│ email (UNIQUE)          │
│ birth_date              │
│ missed_appointments     │
└─────────────┬───────────┘
│ 1
│
│ *
┌─────────────▼───────────┐
│      appointments       │
│─────────────────────────│
│ id (UUID) PK            │
│ patient_id (FK)         │───► patients.id
│ doctor_id (FK)          │───► doctors.id
│ date_time               │
│ status (ENUM)           │
│ notes                   │
└─────────────▲───────────┘
│ *
│
│ 1
┌─────────────┴───────────┐
│        doctors           │
│─────────────────────────│
│ id (UUID) PK            │
│ name                    │
│ specialty               │
│ email (UNIQUE)          │
│ phone                   │
└─────────────────────────┘
```

## 🔗 Relaciones
```
Patient → Appointment
1:N → Un paciente puede tener muchas citas.

Doctor → Appointment
1:N → Un doctor puede atender muchas citas.

Appointment → Patient & Doctor
N:1 → Cada cita pertenece a un paciente y un doctor.
```

## 📊 Swagger

Al ejecutar la aplicación:

http://localhost:8080/swagger-ui/index.html

## ✉️ Recordatorios automáticos

Se usa Spring Mail. Configura en application.yml:
```
spring:
mail:
host: smtp.gmail.com
port: 587
username: tu_correo@gmail.com
password: tu_password
properties:
mail.smtp.auth: true
mail.smtp.starttls.enable: true
```
## 🤖 IA: Predicción de no-show

Ejemplo de uso en código:
```
@Autowired
private NoShowPredictionService predictionService;

@GetMapping("/appointments/{id}/predict")
public ResponseEntity<Double> predict(@PathVariable UUID id) {
Appointment appt = appointmentRepository.findById(id).orElseThrow();
return ResponseEntity.ok(predictionService.predictNoShowProbability(appt));
}
```
### 🔗 Integración con MCP + n8n
Flujo de trabajo:
```
Spring API expone endpoints REST (/patients, /appointments, /predict).

MCP mapea esos endpoints como "tools".

n8n consume MCP vía HTTP Node o Webhook.
```
Ejemplo en n8n HTTP Node:

POST http://localhost:8080/api/appointments
{
"patientId": "123",
"doctorId": "456",
"dateTime": "2025-09-20T10:00"
}

### ▶️ Cómo ejecutar

Clonar el repositorio

git clone https://github.com/tuusuario/medical-appointments-api.git
cd medical-appointments-api


Levantar PostgreSQL en Docker
```
docker run --name medical-db \
-e POSTGRES_USER=admin \
-e POSTGRES_PASSWORD=secret \
-e POSTGRES_DB=medical \
-p 5432:5432 \
-d postgres:15
```

### Ejecutar la aplicación

mvn spring-boot:run


Probar API

Swagger: http://localhost:8080/swagger-ui/index.html

Health check: http://localhost:8080/actuator/health

### 📌 Futuras mejoras

Migración a microservicios (citas, pacientes, notificaciones separados).

IA avanzada con MLFlow o integración a un microservicio en Python (FastAPI + scikit-learn).

Autenticación con Keycloak.

Orquestación en Kubernetes.

### 👨‍💻 Autor

Proyecto desarrollado por Danilo Rivera
📧 rivera.informatica17@gmail.com


