package com.group3.controllers;

import com.group3.factories.RoomFactory;
import com.group3.objects.DisplayUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.util.Optional;

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
