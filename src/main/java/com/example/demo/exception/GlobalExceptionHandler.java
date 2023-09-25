package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotProvidedException.class)
    protected ResponseEntity<Object> handleNotProvidedException(NotProvidedException ex) {
        String errorMessage = "Resource not provided: " + ex.getMessage();
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
    }
    @ExceptionHandler(WrongInputException.class)
    protected ResponseEntity<Object> handleWrongInputException(WrongInputException ex) {
        String errorMessage = "Wrong input: " + ex.getMessage();
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
    }
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        String errorMessage = "Resource not found: " + ex.getMessage();
        return buildErrorResponse(HttpStatus.NOT_FOUND, errorMessage);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Set<String> errorsSet = new HashSet<>();
        ex.getBindingResult().getAllErrors().forEach((er) -> {
            String errorMessage = er.getDefaultMessage();
            Object[] arguments = er.getArguments();
            DefaultMessageSourceResolvable field = (DefaultMessageSourceResolvable) Objects.requireNonNull(arguments)[0];
            String fieldMessage = field.getDefaultMessage();
            errorsSet.add(fieldMessage + ": " + errorMessage);
        });
        return buildErrorArrayResponse(errorsSet.toArray(new String[0]));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        String errorMessage = "An error occurred: " + ex.getMessage();
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
    }
    private static ResponseEntity<Object> buildErrorResponse(HttpStatus httpStatus, String message) {
        ErrorResponse response = new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), message);
        return ResponseEntity.status(httpStatus.value()).body(response);
    }
    private static ResponseEntity<Object> buildErrorArrayResponse(String[] message) {
        ErrorArrayResponse arrayResponse = new ErrorArrayResponse(
                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(arrayResponse);
    }
    @Getter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class ErrorResponse {
        private int status;
        private String error;
        private String message;
    }

    @Getter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class ErrorArrayResponse {
        private int status;
        private String error;
        private String[] message;
    }
}
