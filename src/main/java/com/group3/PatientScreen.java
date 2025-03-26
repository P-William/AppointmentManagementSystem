package com.group3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PatientScreen extends Application {
    @FXML
    public TextField searchField;
    @FXML private ToggleButton calendarToggle;
    @FXML private VBox calendarDropdown;

    @FXML
    public void initialize() {
        calendarToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            calendarDropdown.setVisible(newVal);
            calendarDropdown.setManaged(newVal);
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group3/patientSearchLayout.fxml"));
        BorderPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 1270, 1024);

        // Add CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/group3/patientSearchStyle.css")).toExternalForm());
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

    public void searchEntered(ActionEvent actionEvent) {
        System.out.println("Search term: "+searchField.getText());
    }
}
