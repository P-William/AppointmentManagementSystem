package com.group3.objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Patient {
    @Setter(AccessLevel.NONE)
    private Long patientId;
    private String primaryLanguage;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    private List<String> allergies;
    private List<String> medicalConditions;
    private List<String> medications;

    private List<Appointment> appointments;
}
