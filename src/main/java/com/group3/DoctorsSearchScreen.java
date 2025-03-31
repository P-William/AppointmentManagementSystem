package com.group3;

import com.group3.objects.ApplicationState;
import com.group3.objects.Doctor;
import com.group3.objects.DoctorObjectFactory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.print.Doc;
import java.io.IOException;
import java.util.Objects;

public class DoctorsSearchScreen extends BaseController{
    @FXML
    public TextField searchField;
    @FXML
    public VBox doctorList;
    @FXML private ToggleButton calendarToggle;
    @FXML private VBox calendarDropdown;

    private DoctorObjectFactory doctorObjectFactory;
    private ApplicationState applicationState;

    @FXML
    public void initialize() {
        calendarToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            calendarDropdown.setVisible(newVal);
            calendarDropdown.setManaged(newVal);
        });

        applicationState = ApplicationState.loadState();

        doctorObjectFactory = new DoctorObjectFactory(this);
        doctorObjectFactory.populateDoctors(doctorList, applicationState.getDoctors());
    }

    public void searchEntered(ActionEvent actionEvent) {
        System.out.println("Search term: "+searchField.getText());
    }

    public void viewDoctor(ActionEvent actionEvent, Doctor doctor) throws IOException {
        App.loadDoctorView(doctor, applicationState);
    }
}
