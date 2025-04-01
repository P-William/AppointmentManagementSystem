package com.group3;

import com.group3.factories.RoomFactory;
import com.group3.objects.ApplicationState;
import com.group3.objects.PatientObjectFactory;
import com.group3.objects.Room;
import com.group3.objects.RoomObjectFactory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RoomsSearchScreen extends BaseController {
    @FXML private ToggleButton createToggle;
    @FXML private VBox createDropdown;
    @FXML
    public TextField searchField;
    @FXML
    public VBox roomList;
    @FXML private ToggleButton calendarToggle;
    @FXML private VBox calendarDropdown;

    private ApplicationState applicationState;
    private RoomObjectFactory roomObjectFactory;

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


        applicationState = ApplicationState.loadState();

        applicationState.addRoom(RoomFactory.createRoom("Surgery"));

        roomObjectFactory = new RoomObjectFactory(this);
        roomObjectFactory.populateRooms(roomList, applicationState.getRooms());
    }

    public void searchEntered(ActionEvent actionEvent) {
        System.out.println("Search term: "+searchField.getText());
    }

    public void viewRoom(ActionEvent actionEvent, Room room) throws IOException {
        App.loadRoomView(room, applicationState);
    }
}
