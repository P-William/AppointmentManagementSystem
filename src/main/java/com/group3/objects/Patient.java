package com.group3.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(Include.NON_EMPTY)
public class Patient {
    @Setter(AccessLevel.NONE)
    private UUID patientId;
    private String primaryLanguage;

    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    @Builder.Default
    private List<String> allergies = new ArrayList<>();
    @Builder.Default
    private List<String> medicalConditions = new ArrayList<>();
    @Builder.Default
    private List<String> medications = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    private List<Appointment> appointments = new ArrayList<>();

    public static Patient create(
        String primaryLanguage,
        String name,
        String email,
        String phoneNumber,
        String address
    ) {
        return Patient.builder()
            .patientId(UUID.randomUUID())
            .primaryLanguage(primaryLanguage)
            .name(name)
            .email(email)
            .phoneNumber(phoneNumber)
            .address(address)
            .build();
    }

    public void addAllergy(String allergy) {
        allergies.add(allergy);
    }

    public void removeAllergy(String allergy) {
        allergies.remove(allergy);
    }

    public void addMedicalCondition(String medicalCondition) {
        medicalConditions.add(medicalCondition);
    }

    public void removeMedicalCondition(String medicalCondition) {
        medicalConditions.remove(medicalCondition);
    }

    public void addMedication(String medication) {
        medications.add(medication);
    }

    public void removeMedication(String medication) {
        medications.remove(medication);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
}
