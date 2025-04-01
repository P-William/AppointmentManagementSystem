package com.group3.controllers;

import com.group3.objects.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

public class RoomsViewScreen extends BaseController {
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML
    public Label name;
    @FXML
    public Label pageTitle;
    @FXML
    private ToggleButton calendarToggle;
    @FXML
    private VBox calendarDropdown;

    private Room room;

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
        applicationState.removeRoom(room);
        applicationState.saveState();
        selectDashboard();
    }

    public void setRoom(Room room){
        this.room = room;

        pageTitle.setText("Room > " + room.getRoomName());
        name.setText(room.getRoomName());
    }

    public void changeName(ActionEvent actionEvent) {
        String newName = showInputDialog(name.getText());
        if (newName != null) {
            room.setRoomName(newName);
            applicationState.saveState();
            name.setText(newName);
        }
    }

    private String showInputDialog(String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle("Edit Room Info");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new name:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
