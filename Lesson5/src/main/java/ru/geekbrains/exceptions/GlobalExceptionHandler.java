package ru.geekbrains.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<?> catchStudentNotFoundException(StudentNotFoundException e){
        return new ResponseEntity<>(new CustomError(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
