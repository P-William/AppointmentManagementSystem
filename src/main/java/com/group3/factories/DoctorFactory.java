package com.group3.factories;

import com.group3.objects.Doctor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class DoctorFactory {

    public static Doctor createDoctor(String name, String email, String phoneNumber) {
        return Doctor.builder()
            .doctorId(UUID.randomUUID())
            .name(name)
            .email(email)
            .phoneNumber(phoneNumber)
            .build();
    }

    public static Doctor createDoctor(String name, String email, String phoneNumber, List<String> specialties) {
        return Doctor.builder()
            .doctorId(UUID.randomUUID())
            .name(name)
            .email(email)
            .phoneNumber(phoneNumber)
            .specialties(specialties)
            .build();
    }

}
