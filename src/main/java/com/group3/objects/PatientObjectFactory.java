package com.group3.objects;

import com.group3.PatientsSearchScreen;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class PatientObjectFactory {
    private final PatientsSearchScreen patientsSearchScreen;

    public void populatePatients(VBox box, List<Patient> patients) {
        for(Patient patient : patients) {
            box.getChildren().addAll(createPatientBox(patient), DisplayUtilities.addSeparator());
        }
    }

    public HBox createPatientBox(Patient patient) {
        Label fullName = new Label(patient.getName());
        Label email = new Label(patient.getEmail());
        Label phone = new Label(patient.getPhoneNumber());
        Label address = new Label(patient.getAddress());

        fullName.getStyleClass().add("name-size");
        email.getStyleClass().add("email-size");
        phone.getStyleClass().add("phone-size");
        address.getStyleClass().add("address-size");

        Button view = new Button();
        view.textProperty().set("View");
        view.getStyleClass().add("btn-default");
        view.setOnAction(e -> {
            try {
                patientsSearchScreen.viewPatient(e, patient);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(fullName, email, phone, address, view);
        hBox.getStyleClass().add("table-entry");
        hBox.maxWidth(100000000);

        return hBox;
    }
}
