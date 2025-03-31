---
title: Doctor Factory
sidebar_label: Doctor Factory
---

# DoctorFactory

The `DoctorFactory` class is a utility class responsible for creating instances of the `Doctor` class. It ensures that each doctor instance is properly initialized with a unique identifier and encapsulated data.

## Features

- Uses `@NoArgsConstructor` with `AccessLevel.NONE` to prevent instantiation.
- Provides static factory methods for doctor creation.
- Generates a unique UUID for each doctor instance.
- Supports creating doctors with or without specialties.

## Methods

```java
public static Doctor createDoctor(String firstName, String lastName, String email, String phoneNumber)
```
Creates a `Doctor` instance with the given basic details and a generated UUID.

```java
public static Doctor createDoctor(String firstName, String lastName, String email, String phoneNumber, List<String> specialties)
```
Creates a `Doctor` instance with the given details, including a list of specialties.

## Usage Example

```java
Doctor doctor1 = DoctorFactory.createDoctor("John", "Doe", "john.doe@example.com", "123-456-7890");
Doctor doctor2 = DoctorFactory.createDoctor("Alice", "Smith", "alice.smith@example.com", "987-654-3210", List.of("Cardiology", "Pediatrics"));
```

## Conclusion

The `DoctorFactory` class provides a structured and efficient way to create `Doctor` objects, ensuring each instance is correctly initialized with a unique identifier and optional specialties.
