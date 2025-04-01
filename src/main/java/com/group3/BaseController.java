package com.group3;

import com.group3.objects.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BaseController {

    protected void switchScene(String baseName) throws IOException {
//        String fxmlPath = String.format("/com/group3/%sLayout.fxml", baseName);
//        String cssPath = String.format("/com/group3/%sStyle.css", baseName);
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
//        BorderPane root = loader.load();
//
//        Scene scene = new Scene(root, 1280, 720);
//        Stage stage = (Stage) calendarDropdown.getScene().getWindow();
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssPath)).toExternalForm());
//
//        stage.setTitle("Doctor Tracker");
//        stage.setScene(scene);
//        stage.show();
        App.setRoot(baseName);
    }

    public void selectDashboard() throws IOException {
        System.out.println("Going to dashboard");
        switchScene("dashboardLayout");
    }

    public void selectCalendar(ActionEvent actionEvent) throws IOException {
        switchScene("calendarLayout");
    }

    public void selectPatients(ActionEvent actionEvent) throws IOException {
        switchScene("patientsSearchLayout");
    }

    public void selectDoctors(ActionEvent actionEvent) throws IOException {
        switchScene("doctorsSearchLayout");
    }

    public void selectRooms(ActionEvent actionEvent) throws IOException {
        switchScene("roomsSearchLayout");
    }

    public void createAppointment(ActionEvent actionEvent) throws IOException {
        switchScene("createAppointmentLayout");
    }

    public void createPatient(ActionEvent actionEvent) throws IOException {
        switchScene("createPatientLayout");
    }

    public void createDoctor(ActionEvent actionEvent) throws IOException {
        switchScene("createDoctorLayout");
    }

    public void createRoom(ActionEvent actionEvent) throws IOException {
        switchScene("createRoomLayout");
    }

//    public void viewDoctor(ActionEvent actionEvent, Doctor doctor) throws IOException {
//        // Load FXML layout
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/doctorsViewLayout.fxml"));
//        BorderPane root = loader.load();
//
//        DoctorsViewScreen screen = loader.getController();
//        screen.setDoctor(doctor);
//        screen.setApplicationState(applicationState);
//
//        // Set up the scene
//        Scene scene = new Scene(root, 1280, 720);
//        Stage stage = (Stage) calendarDropdown.getScene().getWindow();
//        // Add CSS
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/doctorsViewStyle.css")).toExternalForm());
////        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/accord/aCCORD-logo.png"))));
//        // Configure the stage
//        stage.setTitle("Doctor Tracker");
//        stage.setScene(scene);
//        stage.show();
//    }
}
