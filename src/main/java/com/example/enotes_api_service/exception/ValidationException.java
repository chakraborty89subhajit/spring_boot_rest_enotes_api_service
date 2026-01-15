package com.example.enotes_api_service.exception;

import java.util.Map;

public class ValidationException extends RuntimeException {

    private final Map<String, Object> errors;

    public ValidationException(Map<String, Object> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }
}
