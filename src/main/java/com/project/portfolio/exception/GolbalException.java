package com.project.portfolio.exception;


import org.apache.catalina.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GolbalException {

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(
            jakarta.validation.ConstraintViolationException ex) {

        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(error -> error.getPropertyPath() + " : " + error.getMessage())
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {

        String message = "Duplicate value found";

        // Optional: Customize message

        if (ex.getMostSpecificCause().getMessage().contains("user_name")) {
            message = "User Name Already Exists";
        }
        if (ex.getMostSpecificCause().getMessage().contains("email")) {
            message = "Email Already Exists";
        }



        return ResponseEntity.badRequest().body(message(message));
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(message(ex.getMessage()));
    }

/**@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
    private Map<String, Object> message(String message) {
        return Map.of(
                "success", false,   // default
                "message", message
        );
    }

    private Map<String, Object> message(Boolean success, String message) {
        return Map.of(
                "success", success,
                "message", message
        );
    }

}
