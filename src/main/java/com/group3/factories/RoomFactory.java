package com.group3.factories;

import com.group3.objects.Room;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class RoomFactory {

    public static Room createRoom(String roomName) {
        return new Room(
            UUID.randomUUID(),
            roomName
        );
    }

}
