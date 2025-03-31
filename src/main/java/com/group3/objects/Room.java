package com.group3.objects;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Room {
    @Setter(AccessLevel.NONE)
    private UUID roomId;

    private String roomName;

}
