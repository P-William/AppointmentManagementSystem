package com.group3.controllers;

import com.group3.App;
import com.group3.objects.Patient;
import com.group3.objects.PatientObjectFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PatientsSearchScreen extends BaseController {
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML
    public TextField searchField;
    @FXML
    public VBox patientList;
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

        PatientObjectFactory patientObjectFactory = new PatientObjectFactory(this);
        patientObjectFactory.populatePatients(patientList, applicationState.getPatients());
    }

    public void searchEntered(ActionEvent actionEvent) {
        System.out.println("Search term: "+ searchField.getText());
    }

    public void viewPatient(ActionEvent actionEvent, Patient patient) throws IOException {
        App.loadPatientView(patient);
    }
}
