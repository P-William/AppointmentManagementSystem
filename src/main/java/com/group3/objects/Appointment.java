package com.group3.objects;

import com.group3.IdService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
public class Appointment {
    private Long appointmentId;
    private LocalDateTime createdAt;

    private Patient patient;
    private Doctor doctor;
    private Room roomBooked;
    private LocalDateTime appointmentAt;
    private Duration duration;

    private String reasonForVisit;
    private Status status;

    public static Appointment create(Patient patient, Doctor doctor, Room room, LocalDateTime appointmentTime, Duration duration, String reasonForVisit) {
        return Appointment.builder()
            .appointmentId(IdService.getNewId())
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
