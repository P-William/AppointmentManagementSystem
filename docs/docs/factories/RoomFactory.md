---
title: Room Factory
sidebar_label: Room Factory
---

# RoomFactory

The `RoomFactory` class is a utility class responsible for creating instances of the `Room` class. It ensures that each room instance is properly initialized with a unique identifier and encapsulated data.

## Features

- Uses `@NoArgsConstructor` with `AccessLevel.NONE` to prevent instantiation.
- Provides a static factory method for room creation.
- Generates a unique UUID for each room instance.

## Methods

```java
public static Room createRoom(String roomName)
```
Creates a `Room` instance with the given name and a generated UUID.

## Usage Example

```java
Room room = RoomFactory.createRoom("Exam Room 101");
```

## Conclusion

The `RoomFactory` class provides a structured and efficient way to create `Room` objects, ensuring each instance is correctly initialized with a unique identifier.
