---
title: Appointment Factory
sidebar_label: Appointment Factory
---

# AppointmentFactory

The `AppointmentFactory` class is a utility class responsible for creating instances of the `Appointment` class. It ensures that each appointment instance is properly initialized with a unique identifier, timestamp, and default values where necessary.

## Features

- Uses `@NoArgsConstructor` with `AccessLevel.NONE` to prevent instantiation.
- Provides static factory methods for appointment creation.
- Generates a unique UUID for each appointment instance.
- Automatically sets the creation timestamp.
- Supports default appointment duration when not specified.

## Constants

```java
public static final Duration DEFAULT_APPOINTMENT_DURATION = Duration.ofMinutes(30);
```
Defines the default duration for appointments as 30 minutes.

## Methods

```java
public static Appointment createAppointment(
    Patient patient,
    Doctor doctor,
    Room room,
    LocalDateTime appointmentTime,
    Duration duration,
    String reasonForVisit
)
```
Creates an `Appointment` instance with the given details, including a specified duration.

```java
public static Appointment createAppointment(
    Patient patient,
    Doctor doctor,
    Room room,
    LocalDateTime appointmentTime,
    String reasonForVisit
)
```
Creates an `Appointment` instance with the given details, using the default duration of 30 minutes.

## Usage Example

```java
Appointment appointment1 = AppointmentFactory.createAppointment(
    somePatient,
    someDoctor,
    someRoom,
    LocalDateTime.of(2024, 6, 10, 14, 0),
    Duration.ofMinutes(45),
    "Follow-up Consultation"
);

Appointment appointment2 = AppointmentFactory.createAppointment(
    somePatient,
    someDoctor,
    someRoom,
    LocalDateTime.of(2024, 6, 12, 10, 0),
    "Routine Checkup"
);
```

## Conclusion

The `AppointmentFactory` class provides a structured and efficient way to create `Appointment` objects, ensuring each instance is correctly initialized with essential details and default values where applicable.
