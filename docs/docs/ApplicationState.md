---
title: ApplicationState
sidebar_label: ApplicationState
sidebar_position: 3
---

# ApplicationState

The `ApplicationState` class manages the overall state of the application, including rooms, doctors, patients, and appointments. It provides methods for adding, removing, saving, and loading application data.

## Features

- Maintains in-memory lists of rooms, doctors, patients, and appointments.
- Supports adding and removing entities dynamically.
- Handles JSON-based persistence for application state.
- Ensures proper state linkage between doctors, patients, and their appointments.
- Uses private constructors to enforce singleton-like behavior.

## Properties

| Property          | Type                  | Description |
|------------------|----------------------|-------------|
| `rooms`          | `List<Room>`          | List of all available rooms. |
| `doctors`        | `List<Doctor>`        | List of registered doctors. |
| `patients`       | `List<Patient>`       | List of registered patients. |
| `appointments`   | `List<Appointment>`   | List of all scheduled appointments. |

## Methods

```java
public void addRoom(Room room)
```
Adds a new room to the system.

```java
public void addDoctor(Doctor doctor)
```
Adds a new doctor to the system.

```java
public void addPatient(Patient patient)
```
Adds a new patient to the system.

```java
public void addAppointment(Appointment appointment)
```
Adds an appointment and updates the corresponding doctor and patient records.

```java
public void removeRoom(Room room)
```
Removes a room from the system.

```java
public void removeDoctor(Doctor doctor)
```
Removes a doctor from the system.

```java
public void removePatient(Patient patient)
```
Removes a patient from the system.

```java
public void removeAppointment(Appointment appointment)
```
Removes an appointment and updates the corresponding doctor and patient records.

```java
public void saveState() throws JsonFileSaveException
```
Saves the current application state to JSON files.

```java
public static ApplicationState loadState() throws JsonFileLoadException
```
Loads the application state from JSON files if they exist; otherwise, returns a new empty state.

## Usage Example

```java
ApplicationState state = ApplicationState.loadState();

Doctor doctor = new Doctor("John", "Doe", "john.doe@example.com");
Patient patient = new Patient("English", "Jane", "Doe", "jane.doe@example.com", "123-456-7890", "123 Main St");
Room room = new Room("Room 101");
Appointment appointment = Appointment.create(patient, doctor, room, LocalDateTime.now(), Duration.ofMinutes(30), "Checkup");

state.addDoctor(doctor);
state.addPatient(patient);
state.addRoom(room);
state.addAppointment(appointment);

state.saveState();
```

## Conclusion

The `ApplicationState` class serves as a centralized storage and management unit for key application data, ensuring data persistence and structured access to doctors, patients, and appointments.
