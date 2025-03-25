package com.group3.objects;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Room {
    @Setter(AccessLevel.NONE)
    private UUID roomId;

    private String roomName;

    public static Room create(String roomName) {
        return Room.builder()
            .roomId(UUID.randomUUID())
            .roomName(roomName)
            .build();
    }
}
