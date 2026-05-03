package com.brunobarchesi.usuario.controller;

import com.brunobarchesi.usuario.infrastucture.execeptions.ConflictExeception;
import com.brunobarchesi.usuario.infrastucture.execeptions.ResourceNotFoundException;
import com.brunobarchesi.usuario.infrastucture.execeptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictExeception.class)
    public ResponseEntity<String> handleConflitException(ConflictExeception exeception){
        return new ResponseEntity<>(exeception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
