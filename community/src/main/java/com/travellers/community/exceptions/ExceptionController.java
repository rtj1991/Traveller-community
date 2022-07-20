package com.travellers.community.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception() {
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TripNotFoundException.class)
    public ResponseEntity<Object> tripException() {
        return new ResponseEntity<>("Trip not found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = DuplicateEntryException.class)
    public ResponseEntity<Object> duplicateException() {
        return new ResponseEntity<>("Duplicate Entry Found ", HttpStatus.NOT_FOUND);
    }


}
