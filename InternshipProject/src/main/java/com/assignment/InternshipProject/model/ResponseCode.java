package com.assignment.InternshipProject.model;

public enum ResponseCode {
    SUCCESS("0000", "Success"),
    VALIDATION_ERROR("0001", "Input validation error"),
    RUNTIME_ERROR("0002", "An unexpected error occured"),
    USER_NOT_FOUND("0003", "User not found"),
    INVALID_CREDENTIALS("0004", "Invalid username or password"),
    INVALID_SESSIONID("0005", "Invalid sessionId");
    private final String code;
    private final String error;
    ResponseCode(String code, String error) {
        this.code = code;
        this.error = error;
    }
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return error;
    }
}
