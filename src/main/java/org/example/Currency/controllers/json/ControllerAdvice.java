package org.example.Currency.controllers.json;

import org.example.Currency.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import javax.xml.bind.ValidationException;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorMessage handle(IllegalArgumentException e){
        return new ErrorMessage(
                e.getMessage(),
                BAD_REQUEST,
                LocalDateTime.now());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NO_CONTENT)
    public ErrorMessage handle(EntityNotFoundException e){
        return new ErrorMessage(
                e.getMessage(),
                NO_CONTENT,
                LocalDateTime.now());
    }

    @ExceptionHandler(OptimisticLockException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ErrorMessage handle(OptimisticLockException e){
        return new ErrorMessage(
                e.getMessage(),
                PRECONDITION_FAILED,
                LocalDateTime.now());
    }
}
