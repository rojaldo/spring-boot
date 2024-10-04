package com.example.demo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.errors.ErrorDto;


@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto handleException(Exception e) {
        return ErrorDto.builder().status(500).message(e.getMessage()).build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto handleBadRequestException(BadRequestException e) {
        return ErrorDto.builder().status(400).message(e.getMessage()).build();
    }
    
}

class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
