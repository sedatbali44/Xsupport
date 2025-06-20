package com.Xsupport.Entity;

public enum Status {
    OPEN("Open"),
    ANSWERED("Answered"),
    CLOSED("Closed");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getDisplayName() {
        return status;
    }
}