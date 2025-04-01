package com.group3.objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.group3.fileio.JsonFileUtil;
import com.group3.fileio.exceptions.JsonFileLoadException;
import com.group3.fileio.exceptions.JsonFileSaveException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationState {

    @Getter
    private static final ApplicationState instance = loadState();

    private static final String ROOT_DIRECTORY = "./data/";
    private static final String ROOMS_FILE = ROOT_DIRECTORY + "rooms.json";
    private static final String DOCTORS_FILE = ROOT_DIRECTORY + "doctors.json";
    private static final String PATIENTS_FILE = ROOT_DIRECTORY + "patients.json";
    private static final String APPOINTMENTS_FILE = ROOT_DIRECTORY + "appointments.json";

    private final List<Room> rooms = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Patient> patients = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addAppointment(Appointment appointment) {
        doctors.stream()
            .filter(doctor -> doctor.getDoctorId().equals(appointment.getDoctor().getDoctorId()))
            .findFirst()
            .ifPresent(doctor -> doctor.getAppointments().add(appointment));
        patients.stream()
            .filter(patient -> patient.getPatientId().equals(appointment.getPatient().getPatientId()))
            .findFirst()
            .ifPresent(patient -> patient.getAppointments().add(appointment));
        appointments.add(appointment);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public void removeAppointment(Appointment appointment) {
        doctors.stream()
            .filter(doctor -> doctor.getDoctorId().equals(appointment.getDoctor().getDoctorId()))
            .findFirst()
            .ifPresent(doctor -> doctor.getAppointments().remove(appointment));
        patients.stream()
            .filter(patient -> patient.getPatientId().equals(appointment.getPatient().getPatientId()))
            .findFirst()
            .ifPresent(patient -> patient.getAppointments().remove(appointment));
        appointments.remove(appointment);
    }

    public void saveState() throws JsonFileSaveException {
        try {
            saveRooms();
            saveDoctors();
            savePatients();
            saveAppointments();
        } catch (IOException e) {
            throw new JsonFileSaveException("Failed to save the state of the application", e);
        }
    }

    private static ApplicationState loadState() throws JsonFileLoadException {
        if (!allFilesExist()) {
            return new ApplicationState();
        }

        ApplicationState applicationState = new ApplicationState();
        try {
            applicationState.rooms.addAll(loadRooms());
            applicationState.doctors.addAll(loadDoctors());
            applicationState.patients.addAll(loadPatients());
            applicationState.appointments.addAll(loadAppointments());
        } catch (IOException e) {
            throw new JsonFileLoadException("Failed to load the state of the application", e);
        }

        // Assign appointments to patients (They aren't stored in the JSON file due to circular references)
        for (Patient patient : applicationState.patients) {
            for (Appointment appointment : applicationState.appointments) {
                if (appointment.getPatient().getPatientId().equals(patient.getPatientId())) {
                    patient.getAppointments().add(appointment);
                }
            }
        }

        // Assign appointments to doctors (They aren't stored in the JSON file due to circular references)
        for (Doctor doctor : applicationState.doctors) {
            for (Appointment appointment : applicationState.appointments) {
                if (appointment.getDoctor().getDoctorId().equals(doctor.getDoctorId())) {
                    doctor.getAppointments().add(appointment);
                }
            }
        }

        return applicationState;
    }

    private void saveRooms() throws IOException {
        JsonFileUtil.saveToJsonFile(rooms, ROOMS_FILE);
    }

    private void saveDoctors() throws IOException {
        JsonFileUtil.saveToJsonFile(doctors, DOCTORS_FILE);
    }

    private void savePatients() throws IOException {
        JsonFileUtil.saveToJsonFile(patients, PATIENTS_FILE);
    }

    private void saveAppointments() throws IOException {
        JsonFileUtil.saveToJsonFile(appointments, APPOINTMENTS_FILE);
    }

    private static List<Room> loadRooms() throws IOException {
        return JsonFileUtil.loadFromJsonFile(ROOMS_FILE, new TypeReference<List<Room>>() {
        });
    }

    private static List<Doctor> loadDoctors() throws IOException {
        return JsonFileUtil.loadFromJsonFile(DOCTORS_FILE, new TypeReference<List<Doctor>>() {
        });
    }

    private static List<Patient> loadPatients() throws IOException {
        return JsonFileUtil.loadFromJsonFile(PATIENTS_FILE, new TypeReference<List<Patient>>() {
        });
    }

    private static List<Appointment> loadAppointments() throws IOException {
        return JsonFileUtil.loadFromJsonFile(APPOINTMENTS_FILE, new TypeReference<List<Appointment>>() {
        });
    }

    private static boolean allFilesExist() {
        return JsonFileUtil.fileExists(ROOMS_FILE) &&
            JsonFileUtil.fileExists(DOCTORS_FILE) &&
            JsonFileUtil.fileExists(PATIENTS_FILE) &&
            JsonFileUtil.fileExists(APPOINTMENTS_FILE);
    }

}
