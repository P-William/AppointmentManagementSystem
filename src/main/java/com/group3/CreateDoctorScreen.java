package com.group3;

import com.group3.factories.DoctorFactory;
import com.group3.objects.ApplicationState;
import com.group3.objects.DisplayUtilities;
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

import java.io.IOException;
import java.util.*;

public class CreateDoctorScreen extends Application {
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
    public CheckBox checkIn;
    @FXML
    public Label patient;
    @FXML
    public Label doctor;
    @FXML
    public Label room;
    @FXML
    public Label date;
    @FXML
    public Label startTime;
    @FXML
    public Label endTime;
    @FXML
    public Label reasonForVisit;
    @FXML
    public Label specialties;
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML
    private ToggleButton calendarToggle;
    @FXML
    private VBox calendarDropdown;

    private String selectedName;
    private String selectedEmail;
    private String selectedPhone;
    private List<String> selectedSpecialties;

    private ApplicationState applicationState;

    @FXML
    public void initialize() {
        calendarToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            calendarDropdown.setVisible(newVal);
            calendarDropdown.setManaged(newVal);
        });
        pageTitle.setText("Create Doctor");
        createToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            createDropdown.setVisible(newVal);
            createDropdown.setManaged(newVal);
        });

        applicationState = ApplicationState.loadState();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/createDoctorLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1280, 720);

        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/createDoctorStyle.css")).toExternalForm());
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
            selectedPhone = newPhone;
            phone.setText(newPhone);
        }
    }

    public void chooseSpecialties(ActionEvent actionEvent) {
        List<String> currentSpecialties = new ArrayList<>();
        if (!specialties.getText().equals("None")) {
            currentSpecialties = Arrays.asList(specialties.getText().split(",\\s*"));
        }

        List<String> updatedSpecialties = showListPickerDialog("Edit specialties:", currentSpecialties);
        if (updatedSpecialties != null && !updatedSpecialties.isEmpty()) {
            selectedSpecialties = updatedSpecialties;
            specialties.setText(String.join(", ", updatedSpecialties));
        } else {
            specialties.setText("None");
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
        if (selectedName == null || selectedEmail == null || selectedPhone == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Doctor");
            alert.setHeaderText("Name , Email, and Phone Number must be valid");
            alert.show();
        }
        else if (selectedSpecialties == null) {
            applicationState.addDoctor(DoctorFactory.createDoctor(selectedName, selectedEmail, selectedPhone));
        }
        else {
            applicationState.addDoctor(DoctorFactory.createDoctor(selectedName, selectedEmail, selectedPhone, selectedSpecialties));
        }

        DisplayUtilities.displaySuccess();
        applicationState.saveState();
    }



}
