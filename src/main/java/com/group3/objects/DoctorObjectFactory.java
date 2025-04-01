package com.group3.objects;

import com.group3.DoctorsSearchScreen;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class DoctorObjectFactory {
    private final DoctorsSearchScreen doctorsSearchScreen;

    public void populateDoctors(VBox box, List<Doctor> doctors) {
        for(Doctor doctor : doctors) {
            box.getChildren().addAll(createDoctorBox(doctor), DisplayUtilities.addSeparator());
        }
    }

    public HBox createDoctorBox(Doctor doctor) {
        Label name = new Label(doctor.getName());
        Label phone = new Label(doctor.getPhoneNumber());
        Label specialties = new Label(String.join(", ", doctor.getSpecialties()));

        name.getStyleClass().add("name-size");
        phone.getStyleClass().add("phone-size");
        specialties.getStyleClass().add("specialties-size");

        Button view = new Button("View");
        view.getStyleClass().add("btn-default");
        view.setOnAction(e -> {
            try {
                doctorsSearchScreen.viewDoctor(e, doctor);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(name, phone, specialties, view);
        hBox.getStyleClass().add("table-entry");

        return hBox;
    }
}
