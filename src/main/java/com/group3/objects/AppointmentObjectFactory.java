package com.group3.objects;

import com.group3.controllers.DashboardScreen;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class AppointmentObjectFactory {
    private final DashboardScreen dashboardScreen;

    public void populateAppointments(VBox box, List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            box.getChildren().addAll(createAppointmentBox(appointment), DisplayUtilities.addSeparator());
        }
    }

    public HBox createAppointmentBox(Appointment appointment) {
        Label dateTime = new Label(appointment.getAppointmentAt().toString());
        Label patient = new Label(appointment.getPatient().getName());
        Label doctor = new Label(appointment.getDoctor().getName());
        Label room = new Label(appointment.getRoomBooked().getRoomName());

        dateTime.getStyleClass().add("date-size");
        patient.getStyleClass().add("patient-size");
        doctor.getStyleClass().add("doctor-size");
        room.getStyleClass().add("room-size");

        Button view = new Button("View");
        view.getStyleClass().add("btn-default");
        view.setOnAction(e -> {
            try {
                dashboardScreen.viewAppointment(e, appointment);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        HBox hBox = new HBox();
        hBox.getStyleClass().add("table-entry");
        hBox.getChildren().addAll(dateTime, patient, doctor, room, view);

        return hBox;
    }
}
