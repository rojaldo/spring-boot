package com.example.demo.errors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
       if (e instanceof IllegalArgumentException) {
           return ResponseEntity.status(400).body(ErrorDto.builder().message("Bad request").stackTrace(e.getMessage()).status(400).build());
       }else if (e instanceof IllegalStateException) {
           return ResponseEntity.status(409).body(ErrorDto.builder().message("Conflict").stackTrace(e.getMessage()).status(409).build());
       }else if (e instanceof DataIntegrityViolationException) {
           return ResponseEntity.status(409).body(ErrorDto.builder().message("Conflict").stackTrace(e.getMessage()).status(409).build());
       }else {
              return ResponseEntity.status(500).body(ErrorDto.builder().message("Internal server error").stackTrace(e.getMessage()).status(500).build());
         }
    }
    
}
