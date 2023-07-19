package com.example.demoauth.exception.handler;
import com.example.demoauth.controller.TeamController;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes = TeamController.class)

public class TeamExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFoundException(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<?> handleJsonMappingException(){
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
