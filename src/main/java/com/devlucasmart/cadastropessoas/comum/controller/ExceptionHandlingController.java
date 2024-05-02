package com.devlucasmart.cadastropessoas.comum.controller;

import com.devlucasmart.cadastropessoas.comum.exception.ValidacaoException;
import com.devlucasmart.cadastropessoas.comum.model.MessageException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidacaoException.class)
    @ResponseBody
    public List<MessageException> validacaoError(ValidacaoException ex) {
        return Arrays.asList(new MessageException(ex.getMessage()));
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseBody
    public List<MessageException> notFoundError(ChangeSetPersister.NotFoundException ex) {
        return Collections.singletonList(new MessageException(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<MessageException> argumentValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        return result.getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage().toLowerCase().contains("campo")
                        ? new MessageException(e.getDefaultMessage())
                        : new MessageException(e.getField(), "O campo " + e.getField() + " " + e.getDefaultMessage())
                ).collect(Collectors.toList());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<MessageException> argumentValidationError(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        return violations.stream()
                .map(violation -> !ObjectUtils.isEmpty(ex.getMessage())
                        ? new MessageException("O campo " + violation.getPropertyPath().toString()
                        + " " + ex.getMessage())

                        : new MessageException(violation.getPropertyPath().toString(),
                        "O campo " + violation.getPropertyPath().toString() + " " + violation.getMessage()))
                .collect(Collectors.toList());
    }
}