package com.group3;

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

public class PatientsViewScreen extends Application {
    @FXML
    public Label email;
    @FXML
    public Label lastName;
    @FXML
    public Label firstName;
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

    @FXML
    public void initialize() {
        calendarToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            calendarDropdown.setVisible(newVal);
            calendarDropdown.setManaged(newVal);
        });
        pageTitle.setText("Patients > Brooke Cronin");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/patientsViewLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1270, 1024);

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

    public void selectDashboard(ActionEvent actionEvent) throws IOException {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/dashboardLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1270, 1024);
        Stage stage = (Stage) calendarDropdown.getScene().getWindow();
        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/dashboardStyle.css")).toExternalForm());
//        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/accord/aCCORD-logo.png"))));
        // Configure the stage
        stage.setTitle("Doctor Tracker");
        stage.setScene(scene);
        stage.show();
    }



    public void changeFirstName(ActionEvent actionEvent) {
        String newFirstName = showInputDialog("Enter new first name:", firstName.getText());
        if (newFirstName != null) {
            firstName.setText(newFirstName);
        }
    }

    public void changeLastName(ActionEvent actionEvent) {
        String newLastName = showInputDialog("Enter new last name:", lastName.getText());
        if (newLastName != null) {
            lastName.setText(newLastName);
        }
    }

    public void changeEmail(ActionEvent actionEvent) {
        String newEmail = showInputDialog("Enter new email:", email.getText());
        if (newEmail != null) {
            email.setText(newEmail);
        }
    }

    public void changePhone(ActionEvent actionEvent) {
        String newPhone = showInputDialog("Enter new phone number:", phone.getText());
        if (newPhone != null) {
            phone.setText(newPhone);
        }
    }

    public void changeAddress(ActionEvent actionEvent) {
        String newAddress = showInputDialog("Enter new address:", address.getText());
        if (newAddress != null) {
            address.setText(newAddress);
        }
    }

    public void changeAllergies(ActionEvent actionEvent) {
        List<String> currentAllergies = Arrays.asList(allergies.getText().split(",\\s*"));
        List<String> updatedAllergies = showListChangeDialog("Edit allergies:", currentAllergies);
        if (updatedAllergies != null) {
            allergies.setText(String.join(", ", updatedAllergies));
        }
    }

    public void changeMedicalConditions(ActionEvent actionEvent) {
        List<String> currentConditions = Arrays.asList(medicalConditions.getText().split(",\\s*"));
        List<String> updatedConditions = showListChangeDialog("Edit medical conditions:", currentConditions);
        if (updatedConditions != null) {
            medicalConditions.setText(String.join(", ", updatedConditions));
        }
    }

    public void changeMedication(ActionEvent actionEvent) {
        List<String> currentMedication = Arrays.asList(medication.getText().split(",\\s*"));
        List<String> updatedMedication = showListChangeDialog("Edit medications:", currentMedication);
        if (updatedMedication != null) {
            medication.setText(String.join(", ", updatedMedication));
        }
    }

    public void changeLanguage(ActionEvent actionEvent) {
        String newLanguage = showInputDialog("Enter primary language:", language.getText());
        if (newLanguage != null) {
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
