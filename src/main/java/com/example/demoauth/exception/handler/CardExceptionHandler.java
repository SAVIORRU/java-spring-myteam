package com.example.demoauth.exception.handler;

import com.example.demoauth.controller.CardController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes = CardController.class)
public class CardExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFoundException(){
        return ResponseEntity.notFound().build();
    }
}
