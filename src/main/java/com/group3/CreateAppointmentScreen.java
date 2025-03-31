package com.group3;

import com.group3.factories.AppointmentFactory;
import com.group3.objects.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class CreateAppointmentScreen extends Application {
    @FXML
    public Label email;
    @FXML
    public Label lastName;
    @FXML
    public Label firstName;
    @FXML
    public Label phone;
    @FXML
    public Label address;
    @FXML
    public Label allergies;
    @FXML
    public Label medicalConditions;
    @FXML
    public Label medication;
    @FXML
    public Label language;
    @FXML
    public Label pageTitle;
    @FXML
    public CheckBox checkIn;
    @FXML
    public Label patient;
    @FXML
    public Label doctor;
    @FXML
    public Label room;
    @FXML
    public Label date;
    @FXML
    public Label startTime;
    @FXML
    public Label endTime;
    @FXML
    public Label reasonForVisit;
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML
    private ToggleButton calendarToggle;
    @FXML
    private VBox calendarDropdown;

    private Patient selectedPatient;
    private Doctor selectedDoctor;
    private Room selectedRoom;
    private LocalDate selectedDate;
    private LocalTime selectedStartTime;
    private LocalTime selectedEndTime;
    private Duration duration;
    private String selectedReason;

    private ApplicationState applicationState;

    @FXML
    public void initialize() {
        calendarToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            calendarDropdown.setVisible(newVal);
            calendarDropdown.setManaged(newVal);
        });
        pageTitle.setText("Create Appointment");

        applicationState = ApplicationState.loadState();
        createToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            createDropdown.setVisible(newVal);
            createDropdown.setManaged(newVal);
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/createAppointmentLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1280, 720);

        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/createAppointmentStyle.css")).toExternalForm());
//        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/accord/aCCORD-logo.png"))));
        // Configure the stage
        primaryStage.setTitle("Doctor Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private void switchScene(String baseName) throws IOException {
        String fxmlPath = String.format("/com/group3/%sLayout.fxml", baseName);
        String cssPath = String.format("/com/group3/%sStyle.css", baseName);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        BorderPane root = loader.load();

        Scene scene = new Scene(root, 1280, 720);
        Stage stage = (Stage) calendarDropdown.getScene().getWindow();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssPath)).toExternalForm());

        stage.setTitle("Doctor Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public void selectDashboard(ActionEvent actionEvent) throws IOException {
        switchScene("dashboard");
    }

    public void selectCalendar(ActionEvent actionEvent) throws IOException {
        switchScene("calendar");
    }

    public void selectPatients(ActionEvent actionEvent) throws IOException {
        switchScene("patientsSearch");
    }

    public void selectDoctors(ActionEvent actionEvent) throws IOException {
        switchScene("doctorsSearch");
    }

    public void selectRooms(ActionEvent actionEvent) throws IOException {
        switchScene("roomsSearch");
    }

    public void createAppointment(ActionEvent actionEvent) throws IOException {
        switchScene("createAppointment");
    }

    public void createPatient(ActionEvent actionEvent) throws IOException {
        switchScene("createPatient");
    }

    public void createDoctor(ActionEvent actionEvent) throws IOException {
        switchScene("createDoctor");
    }

    public void createRoom(ActionEvent actionEvent) throws IOException {
        switchScene("createRoom");
    }

    public void choosePatient(ActionEvent actionEvent) {
        selectedPatient = showPatientPickerDialog(applicationState.getPatients());
        if (selectedPatient != null) {
            patient.setText(selectedPatient.getName());
        }
    }

    public void chooseDoctor(ActionEvent actionEvent) {
        selectedDoctor = showDoctorPickerDialog(applicationState.getDoctors());
        if (selectedDoctor != null) {
            doctor.setText(selectedDoctor.getName());
        }
    }

    public void chooseRoom(ActionEvent actionEvent) {
        selectedRoom = showRoomPickerDialog(applicationState.getRooms());
        if (selectedRoom != null) {
            room.setText(selectedRoom.getRoomName());
        }
    }

    public void chooseReasonForVisit(ActionEvent actionEvent) {
        String newReasonForVisit = showInputDialog("Enter new reason for visit:", reasonForVisit.getText());
        if (newReasonForVisit != null) {
            selectedReason = newReasonForVisit;
            reasonForVisit.setText(newReasonForVisit);
        }
    }

    public void chooseDate(ActionEvent actionEvent) {
        LocalDate currentDate;

        try {
            currentDate = LocalDate.parse(date.getText());
        } catch (Exception e) {
            currentDate = LocalDate.now();
        }

        LocalDate newDate = showDatePickerDialog("Select appointment date:", currentDate);
        if (newDate != null) {
            selectedDate = newDate;
            date.setText(newDate.toString());
        }
    }

    public void chooseStartTime(ActionEvent actionEvent) {
        String currentStart = startTime.getText();
        LocalTime newTime = showTimePickerDialog("Select start time:", currentStart);
        if (newTime != null) {
            selectedStartTime = newTime;
            startTime.setText(newTime.toString());
        }
    }

    public void chooseEndTime(ActionEvent actionEvent) {
        String currentEnd = endTime.getText();
        LocalTime newTime = showTimePickerDialog("Select end time:", currentEnd);
        if (newTime != null) {
            selectedEndTime = newTime;
            endTime.setText(newTime.toString());
        }
    }

    private String showTextPickerDialog(String message, String defaultText, List<String> textOptions) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Edit Appointment Info");
        dialog.setHeaderText(message);

        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(textOptions));
        comboBox.setEditable(false);
        comboBox.setValue(defaultText);

        dialog.getDialogPane().setContent(comboBox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return comboBox.getValue();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private Patient showPatientPickerDialog(List<Patient> patients) {
        Dialog<Patient> dialog = new Dialog<>();
        dialog.setTitle("Edit Appointment Info");
        dialog.setHeaderText("Edit Patient Choice: ");

        ComboBox<Patient> comboBox = new ComboBox<>(FXCollections.observableArrayList(patients));
        comboBox.setEditable(false);

        if (!patients.isEmpty()) {
            comboBox.setValue(patients.get(0));
        }

        comboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Patient patient) {
                return (patient != null) ? patient.getName() : "";
            }

            @Override
            public Patient fromString(String s) {
                return null;
            }
        });

        dialog.getDialogPane().setContent(comboBox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return comboBox.getValue();
            }
            return null;
        });

        Optional<Patient> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private Doctor showDoctorPickerDialog(List<Doctor> doctors) {
        Dialog<Doctor> dialog = new Dialog<>();
        dialog.setTitle("Edit Appointment Info");
        dialog.setHeaderText("Edit Doctor Choice: ");

        ComboBox<Doctor> comboBox = new ComboBox<>(FXCollections.observableArrayList(doctors));
        comboBox.setEditable(false);

        if (!doctors.isEmpty()) {
            comboBox.setValue(doctors.get(0));
        }

        comboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Doctor doctor) {
                return (doctor != null) ? doctor.getName() : "";
            }

            @Override
            public Doctor fromString(String s) {
                return null;
            }
        });

        dialog.getDialogPane().setContent(comboBox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return comboBox.getValue();
            }
            return null;
        });

        Optional<Doctor> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private Room showRoomPickerDialog(List<Room> rooms) {
        Dialog<Room> dialog = new Dialog<>();
        dialog.setTitle("Edit Appointment Info");
        dialog.setHeaderText("Edit Room Choice: ");

        ComboBox<Room> comboBox = new ComboBox<>(FXCollections.observableArrayList(rooms));
        comboBox.setEditable(false);

        if (!rooms.isEmpty()) {
            comboBox.setValue(rooms.get(0));
        }

        comboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Room room) {
                return (room != null) ? room.getRoomName() : "";
            }

            @Override
            public Room fromString(String s) {
                return null;
            }
        });

        dialog.getDialogPane().setContent(comboBox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return comboBox.getValue();
            }
            return null;
        });

        Optional<Room> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private String showInputDialog(String message, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle("Edit Appointment Info");
        dialog.setHeaderText(null);
        dialog.setContentText(message);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private LocalDate showDatePickerDialog(String message, LocalDate defaultDate) {
        Dialog<LocalDate> dialog = new Dialog<>();
        dialog.setTitle("Edit Appointment Info");
        dialog.setHeaderText(message);

        DatePicker datePicker = new DatePicker(defaultDate);
        dialog.getDialogPane().setContent(datePicker);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return datePicker.getValue();
            }
            return null;
        });

        Optional<LocalDate> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private LocalTime showTimePickerDialog(String message, String defaultTime) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Edit Appointment Info");
        dialog.setHeaderText(message);

        List<String> timeOptions = new ArrayList<>();
        for (int hour = 8; hour < 18; hour++) {
            timeOptions.add(String.format("%02d:00", hour));
            timeOptions.add(String.format("%02d:15", hour));
            timeOptions.add(String.format("%02d:30", hour));
            timeOptions.add(String.format("%02d:45", hour));
        }

        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(timeOptions));
        comboBox.setEditable(false);
        comboBox.setValue(defaultTime);

        dialog.getDialogPane().setContent(comboBox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return comboBox.getValue();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        return result.map(LocalTime::parse).orElse(null);
    }

    public void finalizeCreation(ActionEvent actionEvent) {
        if (selectedPatient == null || selectedDoctor == null || selectedRoom == null || selectedReason == null || selectedDate == null || selectedStartTime == null || selectedEndTime == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Appointment");
            alert.setHeaderText("All fields must be valid");
            alert.show();
        }
        else {
            Duration dur = Duration.between(selectedStartTime, selectedEndTime);
            LocalDateTime time = selectedDate.atTime(selectedStartTime);

            DisplayUtilities.displaySuccess();

            applicationState.addAppointment(AppointmentFactory.createAppointment(selectedPatient, selectedDoctor, selectedRoom, time, dur, selectedReason));
            applicationState.saveState();
        }
    }
}
