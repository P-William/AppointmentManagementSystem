package com.group3.fileio.exceptions;

public class JsonFileSaveException extends RuntimeException {
    public JsonFileSaveException(String message) {
        super(message);
    }

    public JsonFileSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
