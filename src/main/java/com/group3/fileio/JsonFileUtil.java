package com.group3.fileio;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class JsonFileUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper()
        .enable(SerializationFeature.INDENT_OUTPUT)
        .registerModule(new JavaTimeModule());

    public static <T> void saveToJsonFile(List<T> list, String filePath) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        // Check if the parent directory exists, if not, create it
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        objectMapper.writeValue(file, list);
    }

    public static <T> List<T> loadFromJsonFile(String filePath, TypeReference<List<T>> typeReference) throws IOException {
        return objectMapper.readValue(new File(filePath), typeReference);
    }

    public static boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }
}