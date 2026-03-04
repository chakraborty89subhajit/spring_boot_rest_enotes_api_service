package com.example.enotes_api_service.exception;

import com.example.enotes_api_service.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 🔹 Resource Not Found (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e){
        log.error("ResourceNotFoundException :: {}", e.getMessage());
        return CommonUtil.createErrorResponseMessage(
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    // 🔹 Validation Errors (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        Map<String, String> errors = new LinkedHashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return CommonUtil.createErrorResponse(
                errors,
                HttpStatus.BAD_REQUEST
        );
    }

    // 🔹 Custom Validation Exception (400)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        return CommonUtil.createErrorResponse(
                ex.getErrors(),
                HttpStatus.BAD_REQUEST
        );
    }

    // 🔹 Invalid JSON (400)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidJson(HttpMessageNotReadableException ex) {

        return CommonUtil.createErrorResponseMessage(
                "Invalid JSON format",
                HttpStatus.BAD_REQUEST
        );
    }

    // 🔹 File Not Found (404)
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException e){
        return CommonUtil.createErrorResponseMessage(
                e.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    // 🔹 Business Rule / Runtime Errors (400)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e){
        log.error("RuntimeException :: {}", e.getMessage());
        return CommonUtil.createErrorResponseMessage(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    // 🔹 Generic Exception (500) - KEEP THIS LAST
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        log.error("Unhandled Exception :: ", e);
        return CommonUtil.createErrorResponseMessage(
                "Something went wrong",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}