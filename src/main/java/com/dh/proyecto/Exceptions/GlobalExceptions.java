package com.dh.proyecto.Exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions extends Exception{

    private static final Logger logger = Logger.getLogger(GlobalExceptions.class);
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> manejarError(BadRequestException ex){
        logger.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
