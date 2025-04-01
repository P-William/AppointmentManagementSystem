package com.group3;

import com.group3.objects.ApplicationState;
import com.group3.objects.Appointment;
import com.group3.objects.AppointmentObjectFactory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class DashboardScreen extends BaseController{
    @FXML
    public VBox appointmentList;

    @FXML
    public Label patientCount;

    @FXML
    public Label doctorCount;

    @FXML
    public Label roomCount;

    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML private ToggleButton calendarToggle;
    @FXML private VBox calendarDropdown;

    private ApplicationState applicationState;
    private AppointmentObjectFactory appointmentObjectFactory;

    @FXML
    public void initialize() {
        calendarToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            calendarDropdown.setVisible(newVal);
            calendarDropdown.setManaged(newVal);
        });

        createToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            createDropdown.setVisible(newVal);
            createDropdown.setManaged(newVal);
        });

        applicationState = ApplicationState.loadState();
        appointmentObjectFactory = new AppointmentObjectFactory(this);
        appointmentObjectFactory.populateAppointments(appointmentList, applicationState.getAppointments());

        patientCount.setText(String.valueOf(applicationState.getPatients().size()));
        doctorCount.setText(String.valueOf(applicationState.getDoctors().size()));
        roomCount.setText(String.valueOf(applicationState.getRooms().size()));
    }

    public void viewAppointment(ActionEvent actionEvent, Appointment appointment) throws IOException {
        App.loadAppointmentView(appointment, applicationState);
    }

}
