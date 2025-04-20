package com.example.taskManager.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class CreateMessageError {
    public static String createErrorMessage(BindingResult bindingResult){
        StringBuilder error = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();

        for (FieldError fieldError : errors){
            error.append("Error on field: " + fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append(";    ");
        }

        return errors.toString();
    }
}
