package com.example.grouptransportapi.exception;


import com.example.grouptransportapi.bean.GroupErrorResponse;
import com.example.grouptransportapi.handler.ListEmptyException;
import com.example.grouptransportapi.handler.ResourceNotFoundException;
import com.example.grouptransportapi.handler.UniqueValidationException;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GroupRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<GroupErrorResponse> handleException(ListEmptyException e) {
        GroupErrorResponse error = new GroupErrorResponse
                (
                        HttpStatus.NO_CONTENT.value(),
                        e.getMessage(),
                        ZonedDateTime.now(ZoneId.of("Z"))
                );
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    public ResponseEntity<GroupErrorResponse> handleException(ResourceNotFoundException e) {
        GroupErrorResponse error = new GroupErrorResponse
                (
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        ZonedDateTime.now(ZoneId.of("Z"))
                );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<GroupErrorResponse> handleException(UniqueValidationException e){
        GroupErrorResponse error = new GroupErrorResponse
                (
                        HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        e.getMessage(),
                        ZonedDateTime.now(ZoneId.of("Z"))
                );
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
