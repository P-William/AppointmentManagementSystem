package com.group3.objects;

import com.group3.IdService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
public class Room {
    @Setter(AccessLevel.NONE)
    private Long roomId;

    private String roomName;

    public static Room create(String roomName) {
        return Room.builder()
            .roomId(IdService.getNewId())
            .roomName(roomName)
            .build();
    }
}
