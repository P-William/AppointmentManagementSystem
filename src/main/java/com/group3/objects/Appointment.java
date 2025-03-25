package com.group3.objects;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Appointment {
    private UUID appointmentId;
    private LocalDateTime createdAt;

    private Patient patient;
    private Doctor doctor;
    private Room roomBooked;
    private LocalDateTime appointmentAt;
    private Duration duration;

    private String reasonForVisit;
    private Status status;

    public static Appointment create(
        Patient patient,
        Doctor doctor,
        Room room,
        LocalDateTime appointmentTime,
        Duration duration,
        String reasonForVisit
    ) {
        return Appointment.builder()
            .appointmentId(UUID.randomUUID())
            .createdAt(LocalDateTime.now())
            .patient(patient)
            .doctor(doctor)
            .roomBooked(room)
            .appointmentAt(appointmentTime)
            .duration(duration)
            .reasonForVisit(reasonForVisit)
            .status(Status.PENDING)
            .build();
    }

    public enum Status {
        PENDING,
        COMPLETE,
        CANCELED
    }
}
