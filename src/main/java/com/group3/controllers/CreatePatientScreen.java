package com.group3.controllers;

import com.group3.factories.PatientFactory;
import com.group3.objects.DisplayUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CreatePatientScreen extends BaseController {
    @FXML
    public Label email;
    @FXML
    public Label name;
    @FXML
    public Label phone;
    @FXML
    public Label address;
    @FXML
    public Label allergies;
    @FXML
    public Label medicalConditions;
    @FXML
    public Label medication;
    @FXML
    public Label language;
    @FXML
    public Label pageTitle;
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML
    private ToggleButton calendarToggle;
    @FXML
    private VBox calendarDropdown;

    private String selectedName;
    private String selectedEmail;
    private String selectedPhoneNum;
    private String selectedAddress;
    private List<String> selectedMedicalConditions;
    private List<String> selectedMedications;
    private List<String> selectedAllergies;
    private String selectedLanguage;

    @FXML
    public void initialize() {
        calendarToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            calendarDropdown.setVisible(newVal);
            calendarDropdown.setManaged(newVal);
        });
        pageTitle.setText("Create Patient");
        createToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            createDropdown.setVisible(newVal);
            createDropdown.setManaged(newVal);
        });
    }

    public void chooseName(ActionEvent actionEvent) {
        String newName = showInputDialog("Enter a name:", name.getText());
        if (newName != null) {
            selectedName = newName;
            name.setText(newName);
        }
    }

    public void chooseEmail(ActionEvent actionEvent) {
        String newEmail = showInputDialog("Enter email:", email.getText());
        if (newEmail != null) {
            selectedEmail = newEmail;
            email.setText(newEmail);
        }
    }

    public void choosePhone(ActionEvent actionEvent) {
        String newPhone = showInputDialog("Enter phone number:", phone.getText());
        if (newPhone != null) {
            selectedPhoneNum = newPhone;
            phone.setText(newPhone);
        }
    }

    public void chooseAddress(ActionEvent actionEvent) {
        String newAddress = showInputDialog("Enter address:", address.getText());
        if (newAddress != null) {
            selectedAddress = newAddress;
            address.setText(newAddress);
        }
    }

    public void chooseAllergies(ActionEvent actionEvent) {
        List<String> currentAllergies = Arrays.asList(allergies.getText().split(",\\s*"));
        List<String> newAllergies = showListPickerDialog("Edit allergies:", currentAllergies);
        if (newAllergies != null) {
            selectedAllergies = newAllergies;
            allergies.setText(String.join(", ", newAllergies));
        }
    }

    public void chooseMedicalConditions(ActionEvent actionEvent) {
        List<String> currentConditions = Arrays.asList(medicalConditions.getText().split(",\\s*"));
        List<String> newConditions = showListPickerDialog("Edit medical conditions:", currentConditions);
        if (newConditions != null) {
            selectedMedicalConditions = newConditions;
            medicalConditions.setText(String.join(", ", newConditions));
        }
    }

    public void chooseMedication(ActionEvent actionEvent) {
        List<String> currentMedications = Arrays.asList(medication.getText().split(",\\s*"));
        List<String> newMedications = showListPickerDialog("Edit medications:", currentMedications);
        if (newMedications != null) {
            selectedMedications = newMedications;
            medication.setText(String.join(", ", newMedications));
        }
    }

    public void chooseLanguage(ActionEvent actionEvent) {
        String newLanguage = showInputDialog("Enter primary language:", language.getText());
        if (newLanguage != null) {
            selectedLanguage = newLanguage;
            language.setText(newLanguage);
        }
    }

    private List<String> showListPickerDialog(String message, List<String> defaultValue) {
        Dialog<List<String>> dialog = new Dialog<>();
        dialog.setTitle("Create Patient");
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

    private String showInputDialog(String message, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle("Create Patient");
        dialog.setHeaderText(null);
        dialog.setContentText(message);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    public void finalizeCreation(ActionEvent actionEvent) {
        if (selectedName == null || selectedEmail == null || selectedAddress == null || selectedPhoneNum == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Patient");
            alert.setHeaderText("Name, Email, Phone Number, and Address must be provided");
        }
        else if (selectedLanguage == null) {
            if (selectedMedications != null && selectedMedicalConditions != null && selectedAllergies != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Patient");
                alert.setHeaderText("Primary language is required");
                return;
            }
            applicationState.addPatient(PatientFactory.createPatient(selectedName, selectedEmail, selectedPhoneNum, selectedAddress));
        }
        else if (selectedMedications == null && selectedMedicalConditions == null && selectedAllergies == null) {
            applicationState.addPatient(PatientFactory.createPatient(selectedLanguage, selectedName, selectedEmail, selectedPhoneNum, selectedAddress));
        }
        else if (selectedAllergies != null && selectedMedicalConditions == null && selectedMedications == null) {
            applicationState.addPatient(PatientFactory.createPatientWithAllergy(selectedLanguage, selectedName, selectedEmail, selectedPhoneNum, selectedAddress, selectedAllergies));
        }
        else if (selectedMedications != null && selectedMedicalConditions == null && selectedAllergies == null) {
            applicationState.addPatient(PatientFactory.createPatientWithMedication(selectedLanguage, selectedName, selectedEmail, selectedPhoneNum, selectedAddress, selectedMedications));
        }
        else if (selectedMedicalConditions != null && selectedMedications == null && selectedAllergies == null) {
            applicationState.addPatient(PatientFactory.createPatientWithMedicalCondition(selectedLanguage, selectedName, selectedEmail, selectedPhoneNum, selectedAddress, selectedMedicalConditions));
        }
        else {
            applicationState.addPatient(PatientFactory.createPatient(selectedLanguage, selectedName, selectedEmail, selectedPhoneNum, selectedAddress, selectedAllergies, selectedMedicalConditions, selectedMedications));
        }

        DisplayUtilities.displaySuccess();
        applicationState.saveState();
    }
}
