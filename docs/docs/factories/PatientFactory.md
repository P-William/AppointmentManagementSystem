---
title: Patient Factory
sidebar_label: Patient Factory
---

# PatientFactory

The `PatientFactory` class is a utility class responsible for creating instances of the `Patient` class. It ensures that each patient instance is properly initialized with a unique identifier and encapsulated data.

## Features

- Uses `@NoArgsConstructor` with `AccessLevel.NONE` to prevent instantiation.
- Provides multiple static factory methods for patient creation.
- Generates a unique UUID for each patient instance.
- Supports creating patients with various levels of detail, including allergies, medical conditions, and medications.

## Methods

```java
public static Patient createPatient(
    String primaryLanguage,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String address
)
```
Creates a `Patient` instance with basic details and a generated UUID.

```java
public static Patient createPatient(
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String address
)
```
Creates a `Patient` instance with a default primary language of `English`.

```java
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
)
```
Creates a `Patient` instance with additional medical details.

```java
public static Patient createPatientWithAllergy(
    String primaryLanguage,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String address,
    List<String> allergies
)
```
Creates a `Patient` instance with allergies.

```java
public static Patient createPatientWithMedicalCondition(
    String primaryLanguage,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String address,
    List<String> medicalConditions
)
```
Creates a `Patient` instance with medical conditions.

```java
public static Patient createPatientWithMedication(
    String primaryLanguage,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String address,
    List<String> medications
)
```
Creates a `Patient` instance with medications.

## Usage Example

```java
Patient patient1 = PatientFactory.createPatient("Spanish", "John", "Doe", "john.doe@example.com", "123-456-7890", "456 Elm St");
Patient patient2 = PatientFactory.createPatient("Jane", "Doe", "jane.doe@example.com", "987-654-3210", "789 Oak St");
Patient patient3 = PatientFactory.createPatientWithAllergy("French", "Alice", "Smith", "alice.smith@example.com", "555-123-4567", "101 Pine St", List.of("Peanuts"));
```

## Conclusion

The `PatientFactory` class provides a structured and efficient way to create `Patient` objects with varying levels of detail, ensuring each instance is correctly initialized with a unique identifier and relevant medical information.
