package com.group3.objects;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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

    public enum Status {
        PENDING,
        COMPLETE,
        CANCELED
    }
}
