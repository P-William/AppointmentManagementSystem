package com.group3;

import com.group3.factories.RoomFactory;
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

public class CreateRoomScreen extends BaseController {
    @FXML
    public Label pageTitle;
    @FXML
    public Label roomName;
    @FXML
    private ToggleButton createToggle;
    @FXML
    private VBox createDropdown;
    @FXML
    private ToggleButton calendarToggle;
    @FXML
    private VBox calendarDropdown;

    private String selectedName;

    private ApplicationState applicationState;

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

        applicationState = ApplicationState.loadState();
    }

    public void chooseRoomName(ActionEvent actionEvent) {
        String newRoomName = showInputDialog("Enter room name:", roomName.getText());
        if (newRoomName != null && !newRoomName.trim().isEmpty() && !newRoomName.equals("None")) {
            selectedName = newRoomName;
            roomName.setText(newRoomName.trim());
        }
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
        if (selectedName == null || selectedName.equals("None")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Room");
            alert.setHeaderText("Room name must be valid");
            alert.show();
            return;
        }

        applicationState.addRoom(RoomFactory.createRoom(selectedName));

        DisplayUtilities.displaySuccess();
        applicationState.saveState();
    }



}
