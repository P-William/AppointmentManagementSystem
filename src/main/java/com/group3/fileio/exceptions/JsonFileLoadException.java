package com.group3.fileio.exceptions;

public class JsonFileLoadException extends RuntimeException {
    public JsonFileLoadException(String message) {
        super(message);
    }

    public JsonFileLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
