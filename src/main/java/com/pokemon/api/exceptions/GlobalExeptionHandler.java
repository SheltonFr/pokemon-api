package com.pokemon.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorObject> handlePokemonNotFoundException(PokemonNotFoundException ex){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorObject> handleReviewNotFoundException(ReviewNotFoundException ex){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }
}
