package com.dmt.todo.controller;

import com.dmt.todo.exception.InvalidInputException;
import com.dmt.todo.exception.NoDataFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.text.ParseException;

@ControllerAdvice
public class ExceptionHelper {
    @ExceptionHandler(value = { InvalidInputException.class })
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ParseException.class })
    public ResponseEntity<Object> handleParseException(ParseException ex) {
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoDataFoundException.class })
    public ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException ex) {
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
