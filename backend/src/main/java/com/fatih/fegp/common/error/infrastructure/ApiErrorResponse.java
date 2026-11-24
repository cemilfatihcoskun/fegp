package com.fatih.fegp.common.error.infrastructure;

import java.time.Instant;

public class ApiErrorResponse {
    private final String code;
    private final String message;
    private final int status;
    private final String path;
    private final Instant timestamp;

    public ApiErrorResponse(String code, String message, int status, String path, Instant timestamp) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String path() {
        return path;
    }

    public Instant timestamp() {
        return timestamp;
    }
}
