package com.Xsupport.Exception;

public enum ExceptionMessage {
    INVALID_CREDENTIALS("Invalid credentials"),
    USER_ALREADY_EXISTS("User already exists"),
    USER_NOT_FOUND("User not found"),
    UNAUTHORIZED_ACTION("You do not have permission to perform this action.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}