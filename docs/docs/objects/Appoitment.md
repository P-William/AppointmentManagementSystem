---
title: Appointment
sidebar_label: Appointment
---

# Appointment

The `Appointment` class represents a scheduled meeting between a patient and a doctor, including details like the assigned room, appointment time, duration, and status.

## Features

- Uniquely generated UUID at creation.
- Uses Lombok annotations for cleaner code.
- Encapsulates fields using appropriate access levels.
- Provides builder-based object creation.
- Tracks appointment details, including status and reason for visit.

## Properties

| Property           | Type               | Description |
|-------------------|------------------|-------------|
| `appointmentId`   | `UUID`            | Unique generated identifier. |
| `createdAt`       | `LocalDateTime`   | Timestamp of appointment creation. |
| `patient`         | `Patient`         | The patient scheduled for the appointment. |
| `doctor`          | `Doctor`          | The doctor assigned to the appointment. |
| `roomBooked`      | `Room`            | The room where the appointment takes place. |
| `appointmentAt`   | `LocalDateTime`   | Date and time of the appointment. |
| `duration`        | `Duration`        | Length of the appointment. |
| `reasonForVisit`  | `String`          | Description of the patient's reason for visit. |
| `status`          | `Status`          | Current status of the appointment (PENDING, COMPLETE, CANCELED). |

## Methods

```java
public static Appointment create(Patient patient, Doctor doctor, Room room, LocalDateTime appointmentTime, Duration duration, String reasonForVisit)
```
Creates a new appointment instance with a generated UUID and default status of `PENDING`.

## Enum: Status

The `Status` enum defines possible states of an appointment:

- `PENDING` – The appointment is scheduled but has not yet occurred.
- `COMPLETE` – The appointment has been completed.
- `CANCELED` – The appointment has been canceled.

## Usage Example

```java
Appointment appointment = Appointment.create(
    somePatient,
    someDoctor,
    someRoom,
    LocalDateTime.of(2024, 5, 15, 10, 0),
    Duration.ofMinutes(30),
    "Routine Checkup"
);
```

## Conclusion
The `Appointment` class provides a structured way to manage medical appointments, ensuring proper tracking of patient visits and doctor schedules.
