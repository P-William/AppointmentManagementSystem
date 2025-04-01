package com.group3;

import com.group3.controllers.AppointmentViewScreen;
import com.group3.controllers.DoctorsViewScreen;
import com.group3.controllers.PatientsViewScreen;
import com.group3.controllers.RoomsViewScreen;
import com.group3.objects.Appointment;
import com.group3.objects.Doctor;
import com.group3.objects.Patient;
import com.group3.objects.Room;
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
        scene = new Scene(loadFXML("dashboardLayout"), 1280, 780);
        scene.getStylesheets().add(App.class.getResource("dashboardStyle.css").toExternalForm());
        stage.setTitle("Doctor Tracker");
        stage.setScene(scene);

        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void loadPatientView(Patient patient) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/group3/patientsViewLayout.fxml"));
        BorderPane root = loader.load();

        PatientsViewScreen screen = loader.getController();
        screen.setPatient(patient);

        scene.setRoot(root);
    }

    public static void loadDoctorView(Doctor doctor) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/group3/doctorsViewLayout.fxml"));
        BorderPane root = loader.load();

        DoctorsViewScreen screen = loader.getController();
        screen.setDoctor(doctor);

        scene.setRoot(root);
    }

    public static void loadRoomView(Room room) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/group3/roomsViewLayout.fxml"));
        BorderPane root = loader.load();

        RoomsViewScreen screen = loader.getController();
        screen.setRoom(room);

        scene.setRoot(root);
    }

    public static void loadAppointmentView(Appointment appointment) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/group3/appointmentViewLayout.fxml"));
        BorderPane root = loader.load();

        AppointmentViewScreen screen = loader.getController();
        screen.setAppointment(appointment);

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
