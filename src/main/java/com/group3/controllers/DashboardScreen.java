package com.group3.controllers;

import com.group3.App;
import com.group3.objects.Appointment;
import com.group3.objects.AppointmentObjectFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardScreen extends BaseController {
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

        AppointmentObjectFactory appointmentObjectFactory = new AppointmentObjectFactory(this);
        appointmentObjectFactory.populateAppointments(appointmentList, applicationState.getAppointments());

        patientCount.setText(String.valueOf(applicationState.getPatients().size()));
        doctorCount.setText(String.valueOf(applicationState.getDoctors().size()));
        roomCount.setText(String.valueOf(applicationState.getRooms().size()));
    }

    public void viewAppointment(ActionEvent actionEvent, Appointment appointment) throws IOException {
        App.loadAppointmentView(appointment);
    }

}
