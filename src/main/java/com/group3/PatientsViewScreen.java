package com.group3;

import com.group3.objects.ApplicationState;
import com.group3.objects.Patient;
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
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
public class PatientsViewScreen extends Application {
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
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
    @FXML
    private ToggleButton calendarToggle;
    @FXML
    private VBox calendarDropdown;

    private Patient patient;

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

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/patientsViewLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1280, 720);

        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/patientsViewStyle.css")).toExternalForm());
//        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/accord/aCCORD-logo.png"))));
        // Configure the stage
        primaryStage.setTitle("Doctor Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    private void switchScene(String baseName) throws IOException {
        String fxmlPath = String.format("/com/group3/%sLayout.fxml", baseName);
        String cssPath = String.format("/com/group3/%sStyle.css", baseName);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        BorderPane root = loader.load();

        Scene scene = new Scene(root, 1280, 720);
        Stage stage = (Stage) calendarDropdown.getScene().getWindow();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssPath)).toExternalForm());

        stage.setTitle("Doctor Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        pageTitle.setText("Patients > " + patient.getName());
        name.setText(patient.getName());
        email.setText(patient.getEmail());
        phone.setText(patient.getPhoneNumber());
        address.setText(patient.getAddress());

        List<String> allergyList = patient.getAllergies();
        allergies.setText(String.join(", ", allergyList));

        List<String> conditionsList = patient.getMedicalConditions();
        medicalConditions.setText(String.join(", ", conditionsList));

        List<String> medicationList = patient.getMedications();
        medication.setText(String.join(", ", medicationList));

        language.setText(patient.getPrimaryLanguage());
    }

    public void selectDashboard(ActionEvent actionEvent) throws IOException {
        switchScene("dashboard");
    }

    public void selectCalendar(ActionEvent actionEvent) throws IOException {
        switchScene("calendar");
    }

    public void selectPatients(ActionEvent actionEvent) throws IOException {
        switchScene("patientsSearch");
    }

    public void selectDoctors(ActionEvent actionEvent) throws IOException {
        switchScene("doctorsSearch");
    }

    public void selectRooms(ActionEvent actionEvent) throws IOException {
        switchScene("roomsSearch");
    }

    public void createAppointment(ActionEvent actionEvent) throws IOException {
        switchScene("createAppointment");
    }

    public void createPatient(ActionEvent actionEvent) throws IOException {
        switchScene("createPatient");
    }

    public void createDoctor(ActionEvent actionEvent) throws IOException {
        switchScene("createDoctor");
    }

    public void createRoom(ActionEvent actionEvent) throws IOException {
        switchScene("createRoom");
    }

    public void changeName(ActionEvent actionEvent) {
        String newName = showInputDialog("Enter a new name:", name.getText());
        if (newName != null) {
            patient.setName(newName);
            applicationState.saveState();
            name.setText(newName);
        }
    }

    public void changeEmail(ActionEvent actionEvent) {
        String newEmail = showInputDialog("Enter new email:", email.getText());
        if (newEmail != null) {
            patient.setEmail(newEmail);
            applicationState.saveState();
            email.setText(newEmail);
        }
    }

    public void changePhone(ActionEvent actionEvent) {
        String newPhone = showInputDialog("Enter new phone number:", phone.getText());
        if (newPhone != null) {
            patient.setPhoneNumber(newPhone);
            applicationState.saveState();
            phone.setText(newPhone);
        }
    }

    public void changeAddress(ActionEvent actionEvent) {
        String newAddress = showInputDialog("Enter new address:", address.getText());
        if (newAddress != null) {
            patient.setAddress(newAddress);
            applicationState.saveState();
            address.setText(newAddress);
        }
    }

    public void changeAllergies(ActionEvent actionEvent) {
        List<String> currentAllergies = Arrays.asList(allergies.getText().split(",\\s*"));
        List<String> updatedAllergies = showListChangeDialog("Edit allergies:", currentAllergies);
        if (updatedAllergies != null) {
            patient.setAllergies(updatedAllergies);
            applicationState.saveState();
            allergies.setText(String.join(", ", updatedAllergies));
        }
    }

    public void changeMedicalConditions(ActionEvent actionEvent) {
        List<String> currentConditions = Arrays.asList(medicalConditions.getText().split(",\\s*"));
        List<String> updatedConditions = showListChangeDialog("Edit medical conditions:", currentConditions);
        if (updatedConditions != null) {
            patient.setMedicalConditions(updatedConditions);
            applicationState.saveState();
            medicalConditions.setText(String.join(", ", updatedConditions));
        }
    }

    public void changeMedication(ActionEvent actionEvent) {
        List<String> currentMedication = Arrays.asList(medication.getText().split(",\\s*"));
        List<String> updatedMedication = showListChangeDialog("Edit medications:", currentMedication);
        if (updatedMedication != null) {
            patient.setMedications(updatedMedication);
            applicationState.saveState();
            medication.setText(String.join(", ", updatedMedication));
        }
    }

    public void changeLanguage(ActionEvent actionEvent) {
        String newLanguage = showInputDialog("Enter primary language:", language.getText());
        if (newLanguage != null) {
            patient.setPrimaryLanguage(newLanguage);
            applicationState.saveState();
            language.setText(newLanguage);
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
