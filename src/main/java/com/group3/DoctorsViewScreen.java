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

public class DoctorsViewScreen extends Application {
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

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/doctorsViewLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1280, 720);

        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/doctorsViewStyle.css")).toExternalForm());
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

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;

        pageTitle.setText("Doctor > " + doctor.getName());
        name.setText(doctor.getName());
        email.setText(doctor.getEmail());
        phone.setText(doctor.getPhoneNumber());
        specialities.setText(String.join(", ", doctor.getSpecialties()));
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
