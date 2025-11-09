package com.employee.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
@Hidden
public class EmployeeExceptionHandler{

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(HttpMessageNotReadableException ex){
        return sendResponse("Bad Request",ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        return sendResponse("Not Found", ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(RuntimeException ex){
        return sendResponse("Runtime Exception",ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    ResponseEntity<ExceptionResponse> sendResponse(String errorCode, String message, HttpStatusCode httpStatusCode){
        try{
         return ResponseEntity.status(httpStatusCode).body(new ExceptionResponse(errorCode,new ObjectMapper().readTree(message)));
        }catch (IOException ex){
            return ResponseEntity.status(httpStatusCode).body(new ExceptionResponse(errorCode, new ObjectMapper().createObjectNode().put("error",message)));
        }
    }
}
