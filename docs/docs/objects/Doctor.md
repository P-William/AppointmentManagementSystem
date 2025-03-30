---
title: Doctor
sidebar_label: Doctor
---

# Doctor

The `Doctor` class represents a medical professional with attributes like name, email, phone number, specialties, and appointments.

## Features

- Uniquely generated UUID at creation.
- Uses Lombok annotations for cleaner code.
- Supports JSON serialization with `@JsonInclude`.
- Encapsulates fields using appropriate access levels.
- Provides builder-based object creation.
- Includes methods for managing specialties and appointments.

## Properties

| Property      | Type         | Description |
|--------------|-------------|-------------|
| `doctorId`   | `UUID`       | Unique generated identifier (immutable). |
| `firstName`  | `String`     | First name of the doctor. |
| `lastName`   | `String`     | Last name of the doctor. |
| `email`      | `String`     | Contact email address. |
| `phoneNumber`| `String`     | Contact phone number. |
| `specialties`| `List<String>` | Medical specialties. |
| `appointments` | `List<Appointment>` | List of appointments (ignored in JSON). |

## Methods

```java
public void addSpecialty(String specialty)
```
Adds a specialty to the doctor.

```java
public void removeSpecialty(String specialty)
```
Removes a specialty from the doctor.

```java
public void addAppointment(Appointment appointment)
```
Adds an appointment to the doctor.

```java
public void removeAppointment(Appointment appointment)
```
Removes an appointment from the doctor.

## Usage Example

```java
Doctor doctor = Doctor.create("John", "Doe", "john.doe@example.com", "123-456-7890");
doctor.addSpecialty("Cardiology");
doctor.addAppointment(someAppointment);
```

## Conclusion
The `Doctor` class provides a structured way to manage doctor-related data, ensuring encapsulation and immutability where necessary.
