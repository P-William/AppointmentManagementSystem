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

public class RoomsViewScreen extends Application {
    @FXML
    public Label number;
    @FXML
    public Label name;
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
        pageTitle.setText("Rooms > X-Ray");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/roomsViewLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1270, 1024);

        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/roomsViewStyle.css")).toExternalForm());
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

    public void selectPatients(ActionEvent actionEvent) throws IOException {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/patientsSearchLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1270, 1024);
        Stage stage = (Stage) calendarDropdown.getScene().getWindow();
        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/patientsSearchStyle.css")).toExternalForm());
//        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/accord/aCCORD-logo.png"))));
        // Configure the stage
        stage.setTitle("Doctor Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public void selectDoctors(ActionEvent actionEvent) throws IOException {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/doctorsSearchLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1270, 1024);
        Stage stage = (Stage) calendarDropdown.getScene().getWindow();
        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/doctorsSearchStyle.css")).toExternalForm());
//        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/accord/aCCORD-logo.png"))));
        // Configure the stage
        stage.setTitle("Doctor Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public void selectRooms(ActionEvent actionEvent) throws IOException {
        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/roomsSearchLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1270, 1024);
        Stage stage = (Stage) calendarDropdown.getScene().getWindow();
        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/roomsSearchStyle.css")).toExternalForm());
//        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/accord/aCCORD-logo.png"))));
        // Configure the stage
        stage.setTitle("Doctor Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public void changeName(ActionEvent actionEvent) {
        String newName = showInputDialog("Enter new name:", name.getText());
        if (newName != null) {
            name.setText(newName);
        }
    }

    public void changeNumber(ActionEvent actionEvent) {
        String newNumber = showInputDialog("Enter new number:", number.getText());
        if (newNumber != null) {
            number.setText(newNumber);
        }
    }

    private String showInputDialog(String message, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle("Edit Room Info");
        dialog.setHeaderText(null);
        dialog.setContentText(message);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

}
