---
title: FileIO
sidebar_label: FileIO
sidebar_position: 5
---

# JsonFileUtil

The `JsonFileUtil` class provides utility methods for reading and writing JSON files using Jackson's `ObjectMapper`. It ensures structured data persistence for various entities within the application.

## Features

- Uses Jackson's `ObjectMapper` for JSON serialization and deserialization.
- Supports saving lists of objects to JSON files.
- Handles reading lists of objects from JSON files.
- Ensures the existence of parent directories before saving files.
- Provides a method to check if a file exists.

## Methods

```java
public static <T> void saveToJsonFile(List<T> list, String filePath) throws IOException
```
Saves a list of objects to a specified JSON file. Creates parent directories if they do not exist.

```java
public static <T> List<T> loadFromJsonFile(String filePath, TypeReference<List<T>> typeReference) throws IOException
```
Loads a list of objects from a specified JSON file using the provided `TypeReference`.

```java
public static boolean fileExists(String filePath)
```
Checks if a file exists at the given path.

## Usage Example

```java
List<Doctor> doctors = Arrays.asList(new Doctor("John", "Doe", "john.doe@example.com"));
String filePath = "./data/doctors.json";

// Save doctors list to JSON file
JsonFileUtil.saveToJsonFile(doctors, filePath);

// Load doctors list from JSON file
List<Doctor> loadedDoctors = JsonFileUtil.loadFromJsonFile(filePath, new TypeReference<List<Doctor>>() {});

// Check if file exists
boolean exists = JsonFileUtil.fileExists(filePath);
```

## Conclusion

The `JsonFileUtil` class simplifies JSON-based file operations, making it easy to persist and retrieve application data efficiently.
