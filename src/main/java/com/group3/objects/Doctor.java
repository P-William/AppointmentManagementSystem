package com.group3.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Doctor {
    @Setter(AccessLevel.NONE)
    private UUID doctorId;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @Builder.Default
    private List<String> specialties = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    private List<Appointment> appointments = new ArrayList<>();

    public static Doctor create(
        String firstName,
        String lastName,
        String email,
        String phoneNumber
    ) {
        return Doctor.builder()
            .doctorId(UUID.randomUUID())
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .phoneNumber(phoneNumber)
            .build();
    }

    public void addSpecialty(String specialty) {
        specialties.add(specialty);
    }

    public void removeSpecialty(String specialty) {
        specialties.remove(specialty);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
}
