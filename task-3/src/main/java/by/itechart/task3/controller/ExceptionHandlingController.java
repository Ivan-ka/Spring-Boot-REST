package by.itechart.task3.controller;

import by.itechart.task3.dto.WarningDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WarningDto> handleException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        WarningDto warningDto = new WarningDto(errors);
        return new ResponseEntity<>(warningDto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<WarningDto> handleException(UsernameNotFoundException e){

        Map<String, String> errors = new HashMap<>();
        errors.put("login", e.getMessage());
        WarningDto warningDto = new WarningDto(errors);
        return new ResponseEntity<>(warningDto, HttpStatus.BAD_REQUEST);
    }

}
