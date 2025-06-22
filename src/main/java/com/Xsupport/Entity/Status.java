package com.Xsupport.Entity;

import com.Xsupport.Exception.ExceptionMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    OPEN("Open"),
    ANSWERED("Answered"),
    CLOSED("Closed");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

}
