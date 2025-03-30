---
title: Room
sidebar_label: Room
---

# Room

The `Room` class represents a room entity with a unique ID and a name.

## Features

- Uses Lombok annotations for cleaner code.
- Encapsulates fields using appropriate access levels.
- Provides builder-based object creation.

## Properties

| Property   | Type   | Description |
|-----------|--------|-------------|
| `roomId`  | `UUID`  | Unique generated identifier (immutable). |
| `roomName` | `String` | Name of the room. |

## Usage Example

```java
Room room = Room.create("Exam Room 3");
System.out.println("Room Name: " + room.getRoomName());
```

## Conclusion

The `Room` class provides a simple way to manage room data with unique identifiers.
