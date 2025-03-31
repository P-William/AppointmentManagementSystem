package com.group3.factories;

import com.group3.objects.Patient;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class PatientFactory {

    public static Patient createPatient(
        String primaryLanguage,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address
    ) {
        return Patient.builder()
            .patientId(UUID.randomUUID())
            .primaryLanguage(primaryLanguage)
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .phoneNumber(phoneNumber)
            .address(address)
            .build();
    }

    public static Patient createPatient(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address
    ) {
        return Patient.builder()
            .patientId(UUID.randomUUID())
            .primaryLanguage("English")
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .phoneNumber(phoneNumber)
            .address(address)
            .build();
    }

    public static Patient createPatient(
        String primaryLanguage,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        List<String> allergies,
        List<String> medicalConditions,
        List<String> medications
    ) {
        return Patient.builder()
            .patientId(UUID.randomUUID())
            .primaryLanguage(primaryLanguage)
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .phoneNumber(phoneNumber)
            .address(address)
            .allergies(allergies)
            .medicalConditions(medicalConditions)
            .medications(medications)
            .build();
    }

    public static Patient createPatientWithAllergy(
        String primaryLanguage,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        List<String> allergies
    ) {
        return Patient.builder()
            .patientId(UUID.randomUUID())
            .primaryLanguage(primaryLanguage)
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .phoneNumber(phoneNumber)
            .address(address)
            .allergies(allergies)
            .build();
    }

    public static Patient createPatientWithMedicalCondition(
        String primaryLanguage,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        List<String> medicalConditions
    ) {
        return Patient.builder()
            .patientId(UUID.randomUUID())
            .primaryLanguage(primaryLanguage)
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .phoneNumber(phoneNumber)
            .address(address)
            .medicalConditions(medicalConditions)
            .build();
    }

    public static Patient createPatientWithMedication(
        String primaryLanguage,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        List<String> medications
    ) {
        return Patient.builder()
            .patientId(UUID.randomUUID())
            .primaryLanguage(primaryLanguage)
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .phoneNumber(phoneNumber)
            .address(address)
            .medications(medications)
            .build();
    }
}
