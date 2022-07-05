package com.dh.proyecto.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions extends Exception{

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> manejarError(BadRequestException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
