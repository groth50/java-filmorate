package ru.yandex.practicum.filmorate.exception;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleUserNotFoundException(UserNotFoundException e) {
        return new ErrorMessage(e.getMessage());
    }

    @Value
    public class ErrorMessage {
        String description;
    }
}
