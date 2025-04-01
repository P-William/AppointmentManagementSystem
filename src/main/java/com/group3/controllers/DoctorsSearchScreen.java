package com.group3.controllers;

import com.group3.App;
import com.group3.objects.Doctor;
import com.group3.objects.DoctorObjectFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DoctorsSearchScreen extends BaseController {
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML
    public TextField searchField;
    @FXML
    public VBox doctorList;
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

        DoctorObjectFactory doctorObjectFactory = new DoctorObjectFactory(this);
        doctorObjectFactory.populateDoctors(doctorList, applicationState.getDoctors());
    }

    public void searchEntered(ActionEvent actionEvent) {
        System.out.println("Search term: "+searchField.getText());
    }

    public void viewDoctor(ActionEvent actionEvent, Doctor doctor) throws IOException {
        App.loadDoctorView(doctor);
    }
}
