package de.neuefische.springexceptionhandlingtask;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalExceptionHandler{

    @ExceptionHandler(NoSuchElementException.class)
    public ErrorMessage handleNoSuchElementException(NoSuchElementException e) {
        ErrorMessage errorMessage = new ErrorMessage("Ein Fehler ist aufgetreten: " + e.getMessage(), LocalDateTime.now());
        return errorMessage;
    }

    @ExceptionHandler(Exception.class)
    public ErrorMessage handleException(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage("Dieser Fehler ist aufgetreten: " + e.getClass().getCanonicalName(), LocalDateTime.now());
        return errorMessage;
    }

}
