package com.pick.nalsoom.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomUserInfoException {

    @ExceptionHandler(CustomUserNameNotFoundException.class)
    public ResponseEntity<String> handleUserNameNotFoundException (CustomUserNameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Not Found" + exception.getMessage());
    }

    @ExceptionHandler(CustomJwtException.class)
    public ResponseEntity<String> handleJwtException (CustomJwtException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Not Found" + exception.getMessage());
    }

}
