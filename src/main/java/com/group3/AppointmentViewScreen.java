package com.group3;

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

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class AppointmentViewScreen extends Application {
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
    private ToggleButton calendarToggle;
    @FXML
    private VBox calendarDropdown;

    @FXML
    public void initialize() {
        calendarToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            calendarDropdown.setVisible(newVal);
            calendarDropdown.setManaged(newVal);
        });
        pageTitle.setText("Appointment > Brooke Cronin, Timmy Smith, Room 1 @ 13:00-13:30");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/appointmentViewLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1270, 1024);

        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/appointmentViewStyle.css")).toExternalForm());
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

        Scene scene = new Scene(root, 1270, 1024);
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

    public void markPresent(ActionEvent actionEvent) {
    }

    public void viewPatient(ActionEvent actionEvent) throws IOException {
        switchScene("patientsView");
    }

    public void viewDoctor(ActionEvent actionEvent) throws IOException {
        switchScene("doctorsView");
    }

    public void viewRoom(ActionEvent actionEvent) throws IOException {
        switchScene("roomsView");
    }

    public void changeDate(ActionEvent actionEvent) {
        LocalDate currentDate;

        try {
            currentDate = LocalDate.parse(date.getText());
        } catch (Exception e) {
            currentDate = LocalDate.now();
        }

        LocalDate newDate = showDatePickerDialog("Select appointment date:", currentDate);
        if (newDate != null) {
            date.setText(newDate.toString());
        }
    }


    public void changeStartTime(ActionEvent actionEvent) {
        String currentStart = startTime.getText();
        String newTime = showTimePickerDialog("Select start time:", currentStart);
        if (newTime != null) {
            startTime.setText(newTime);
        }
    }

    public void changeEndTime(ActionEvent actionEvent) {
        String currentEnd = endTime.getText();
        String newTime = showTimePickerDialog("Select end time:", currentEnd);
        if (newTime != null) {
            endTime.setText(newTime);
        }
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

    private String showTimePickerDialog(String message, String defaultTime) {
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
        return result.orElse(null);
    }

}
