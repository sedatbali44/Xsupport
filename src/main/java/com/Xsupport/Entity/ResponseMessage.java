package com.Xsupport.Entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseMessage {
    ALREADY_LOGGED_OUT("Already logged out"),
    LOG_OUT_RESPONSE("Logged out successfully"),
    RECORD_DELETED("Record Deleted!");

    private final String value;

    ResponseMessage(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
