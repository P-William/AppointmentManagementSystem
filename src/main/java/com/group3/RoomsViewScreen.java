package com.group3;

import com.group3.objects.ApplicationState;
import com.group3.objects.Room;
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
        String newName = showInputDialog("Enter new name:", name.getText());
        if (newName != null) {
            room.setRoomName(newName);
            applicationState.saveState();
            name.setText(newName);
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
