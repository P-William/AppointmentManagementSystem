package com.group3.factories;

import com.group3.objects.Appointment;
import com.group3.objects.Appointment.Status;
import com.group3.objects.Doctor;
import com.group3.objects.Patient;
import com.group3.objects.Room;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class AppointmentFactory {

    public static Duration DEFAULT_APPOINTMENT_DURATION = Duration.ofMinutes(30);

    public static Appointment createAppointment(
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

    public static Appointment createAppointment(
        Patient patient,
        Doctor doctor,
        Room room,
        LocalDateTime appointmentTime,
        String reasonForVisit
    ) {
        return Appointment.builder()
            .appointmentId(UUID.randomUUID())
            .createdAt(LocalDateTime.now())
            .patient(patient)
            .doctor(doctor)
            .roomBooked(room)
            .appointmentAt(appointmentTime)
            .duration(DEFAULT_APPOINTMENT_DURATION)
            .reasonForVisit(reasonForVisit)
            .status(Status.PENDING)
            .build();
    }

}
