package com.group3;

import com.group3.objects.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("dashboardLayout"), 1280, 720);
        scene.getStylesheets().add(App.class.getResource("dashboardStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void loadPatientView(Patient patient, ApplicationState applicationState) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/group3/patientsViewLayout.fxml"));
        BorderPane root = loader.load();

        PatientsViewScreen screen = loader.getController();
        screen.setPatient(patient);
        screen.setApplicationState(applicationState);

        scene.setRoot(root);
    }

    static void loadDoctorView(Doctor doctor, ApplicationState applicationState) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/group3/doctorsViewLayout.fxml"));
        BorderPane root = loader.load();

        DoctorsViewScreen screen = loader.getController();
        screen.setDoctor(doctor);
        screen.setApplicationState(applicationState);

        scene.setRoot(root);
    }

    static void loadRoomView(Room room, ApplicationState applicationState) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/group3/roomsViewLayout.fxml"));
        BorderPane root = loader.load();

        RoomsViewScreen screen = loader.getController();
        screen.setRoom(room);
        screen.setApplicationState(applicationState);

        scene.setRoot(root);
    }

    static void loadAppointmentView(Appointment appointment, ApplicationState applicationState) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/group3/appointmentViewLayout.fxml"));
        BorderPane root = loader.load();

        AppointmentViewScreen screen = loader.getController();
        screen.setAppointment(appointment);
        screen.setApplicationState(applicationState);

        scene.setRoot(root);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
