package com.group3;

import com.group3.objects.ApplicationState;
import com.group3.objects.Doctor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.util.*;

public class DoctorsViewScreen extends BaseController {
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML
    public Label email;
    @FXML
    public Label phone;
    @FXML
    public Label address;
    @FXML
    public Label specialities;
    @FXML
    public Label pageTitle;
    @FXML
    public Label name;
    @FXML
    private ToggleButton calendarToggle;
    @FXML
    private VBox calendarDropdown;

    private Doctor doctor;

    @Setter
    private ApplicationState applicationState;

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


    }

    public void delete() throws IOException {
        applicationState.removeDoctor(doctor);
        applicationState.saveState();
        selectDashboard();
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;

        pageTitle.setText("Doctor > " + doctor.getName());
        name.setText(doctor.getName());
        email.setText(doctor.getEmail());
        phone.setText(doctor.getPhoneNumber());
        specialities.setText(String.join(", ", doctor.getSpecialties()));
    }

    public void changeName(ActionEvent actionEvent) {
        String newName = showInputDialog("Enter a new name:", name.getText());
        if (newName != null) {
            doctor.setName(newName);
            applicationState.saveState();
            name.setText(newName);
        }
    }

    public void changeEmail(ActionEvent actionEvent) {
        String newEmail = showInputDialog("Enter new email:", email.getText());
        if (newEmail != null) {
            doctor.setEmail(newEmail);
            applicationState.saveState();
            email.setText(newEmail);
        }
    }

    public void changePhone(ActionEvent actionEvent) {
        String newPhone = showInputDialog("Enter new phone number:", phone.getText());
        if (newPhone != null) {
            doctor.setPhoneNumber(newPhone);
            applicationState.saveState();
            phone.setText(newPhone);
        }
    }

    public void changeSpeciality(ActionEvent actionEvent) {
        List<String> currentSpeciality = Arrays.asList(specialities.getText().split(",\\s*"));
        List<String> updatedSpeciality = showListChangeDialog("Edit specialities:", currentSpeciality);
        if (updatedSpeciality != null) {
            doctor.setSpecialties(updatedSpeciality);
            applicationState.saveState();
            specialities.setText(String.join(", ", updatedSpeciality));
        }
    }


    private String showInputDialog(String message, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle("Edit Patient Info");
        dialog.setHeaderText(null);
        dialog.setContentText(message);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private List<String> showListChangeDialog(String message, List<String> defaultValue) {
        Dialog<List<String>> dialog = new Dialog<>();
        dialog.setTitle("Edit Patient Info");
        dialog.setHeaderText(message);

        // Set the button types
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        // Create the UI elements
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(defaultValue);

        TextField inputField = new TextField();
        inputField.setPromptText("New item...");

        Button addButton = new Button("Add");
        Button removeButton = new Button("Remove Selected");

        // Add button logic
        addButton.setOnAction(e -> {
            String newItem = inputField.getText().trim();
            if (!newItem.isEmpty()) {
                listView.getItems().add(newItem);
                inputField.clear();
            }
        });

        // Remove button logic
        removeButton.setOnAction(e -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                listView.getItems().remove(selected);
            }
        });

        // Layout
        VBox layout = new VBox(8);
        layout.getChildren().addAll(listView, inputField, new HBox(8, addButton, removeButton));
        dialog.getDialogPane().setContent(layout);

        // Convert the result to a list
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return new ArrayList<>(listView.getItems());
            }
            return null;
        });

        Optional<List<String>> result = dialog.showAndWait();
        return result.orElse(defaultValue);
    }

}
