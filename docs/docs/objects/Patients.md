---
title: Patient
sidebar_label: Patient
---

# Patient

The `Patient` class represents a medical patient with attributes like primary language, name, contact details, allergies, medical conditions, and appointments.

## Features

- Uniquely generated UUID at creation.
- Uses Lombok annotations for cleaner code.
- Supports JSON serialization with `@JsonInclude`.
- Encapsulates fields using appropriate access levels.
- Provides builder-based object creation.
- Includes methods for managing allergies, medical conditions, medications, and appointments.

## Properties

| Property           | Type              | Description |
|-------------------|-----------------|-------------|
| `patientId`       | `UUID`           | Unique generated identifier (immutable). |
| `primaryLanguage` | `String`         | Patient's primary language. |
| `firstName`       | `String`         | First name of the patient. |
| `lastName`        | `String`         | Last name of the patient. |
| `email`           | `String`         | Contact email address. |
| `phoneNumber`     | `String`         | Contact phone number. |
| `address`         | `String`         | Home address of the patient. |
| `allergies`       | `List<String>`   | List of known allergies. |
| `medicalConditions` | `List<String>` | List of known medical conditions. |
| `medications`     | `List<String>`   | List of medications the patient is taking. |
| `appointments`    | `List<Appointment>` | List of appointments (ignored in JSON). |

## Methods

```java
public void addAllergy(String allergy)
```
Adds an allergy to the patient's record.

```java
public void removeAllergy(String allergy)
```
Removes an allergy from the patient's record.

```java
public void addMedicalCondition(String medicalCondition)
```
Adds a medical condition to the patient's record.

```java
public void removeMedicalCondition(String medicalCondition)
```
Removes a medical condition from the patient's record.

```java
public void addMedication(String medication)
```
Adds a medication to the patient's record.

```java
public void removeMedication(String medication)
```
Removes a medication from the patient's record.

```java
public void addAppointment(Appointment appointment)
```
Adds an appointment to the patient.

```java
public void removeAppointment(Appointment appointment)
```
Removes an appointment from the patient.

## Usage Example

```java
Patient patient = Patient.create("English", "Jane", "Doe", "jane.doe@example.com", "987-654-3210", "123 Main St");
patient.addAllergy("Peanuts");
patient.addMedicalCondition("Asthma");
patient.addMedication("Albuterol");
patient.addAppointment(someAppointment);
```

## Conclusion

The `Patient` class provides a structured way to manage patient-related data, ensuring encapsulation and immutability where necessary.
