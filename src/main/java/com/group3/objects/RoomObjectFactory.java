package com.group3.objects;

import com.group3.RoomsSearchScreen;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class RoomObjectFactory {
    private final RoomsSearchScreen roomsSearchScreen;

    public void populateRooms(VBox box, List<Room> rooms) {
        for (Room room : rooms) {
            box.getChildren().addAll(createRoomBox(room), DisplayUtilities.addSeparator());
        }
    }

    public HBox createRoomBox(Room room) {
        Label name = new Label(room.getRoomName());
        name.getStyleClass().add("name-size");

        Button view = new Button("View");
        view.setOnAction(e -> {
            try {
                roomsSearchScreen.viewRoom(e, room);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        HBox hBox = new HBox();
        hBox.getStyleClass().add("table-entry");
        hBox.getChildren().addAll(name, view);

        return hBox;
    }
}
