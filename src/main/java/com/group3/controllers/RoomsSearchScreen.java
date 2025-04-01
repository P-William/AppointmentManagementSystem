package com.group3.controllers;

import com.group3.App;
import com.group3.objects.Room;
import com.group3.objects.RoomObjectFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RoomsSearchScreen extends BaseController {
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML
    public TextField searchField;
    @FXML
    public VBox roomList;
    @FXML private ToggleButton calendarToggle;
    @FXML private VBox calendarDropdown;

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

        RoomObjectFactory roomObjectFactory = new RoomObjectFactory(this);
        roomObjectFactory.populateRooms(roomList, applicationState.getRooms());
    }

    public void searchEntered(ActionEvent actionEvent) {
        System.out.println("Search term: "+searchField.getText());
    }

    public void viewRoom(ActionEvent actionEvent, Room room) throws IOException {
        App.loadRoomView(room);
    }
}
