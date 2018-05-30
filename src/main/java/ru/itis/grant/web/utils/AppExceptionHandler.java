package ru.itis.grant.web.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.grant.security.exception.IncorrectDataException;

@RestController
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = IncorrectDataException.class)
    public ResponseEntity<JsonResponse> handleIncorrectDataException(IncorrectDataException e) {
        return new ResponseEntity<>(new JsonResponse(e.getFieldName(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}